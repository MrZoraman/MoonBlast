# MoonBlast
This is the reference implementation for the MoonBlast protocol, named after the 
protocol itself.

The protocol does not have much to offer. It is actually fairly lacking in bells 
and whistles, but its claim to fame is not in features, but in simplicity.

The MoonBlast protocol is *very* basic, and as such there is not that much
that can go wrong. I am not saying that the protocol is perfect or bug free,
just the simple idea that the less there is that can go wrong, generally the
less things that will go wrong.

Being such a small protocol (~18KB), you can be sure it's not going to take up
too much space. This library is *fast*. Not only is it not sending that many
instructions to your CPU, but it is designed with speed in mind, making use
of O(1) time whenever it can. 

# The Protocol
As stated before, the protocol strives to be simple and efficient. 
The MoonBlast protocol contains no fluff. It is as small as it can be without
resorting to compression, or making arbitrary sacrifices, such as maximum
packet size.

If binary data in th MoonBlast protocol is saved to disk, the .mb file extension is reccomended, but not required.

## Packet Layout

Each packet is layed out pretty simply:

| Short Name    | Data Type        | Size in Bytes  | Description  |
| ----------    | -------------    | -------------  | ----- |
| Packet Start  | byte             | 1              | This marks the start of the packet. Its value is '('.  |
| Packet Length | int              | 4              | This is the length of the packet in bytes |
| Version       | byte             | 1              | This is the protocol version. |
| Param Length  | int              | 4              | This is the amount of parameters in the packet. |
| Params        | ...              | ...            | This is a list of parameters. See below for info on each parameter. |
| Packet End    | byte             | 1              | This marks the end of the packet. Its value is ')'. |

Parameters are layed out like so:

| Short Name    | Data Type        | Size in Bytes  | Description  |
| ----------    | -------------    | -------------  | ----- |
| Param Type    | byte             | 1              | This is the type of the parameter. See below for the list of possible values.  |
| Param Data    | ...              | ...            | This is the data for the parameter. Length varies depending on the type. |

*Binary* parameters are layed out like this:

| Short Name    | Data Type        | Size in Bytes  | Description  |
| ----------    | -------------    | -------------  | ----- |
| Param Type    | byte             | 1              | This is the type of the parameter. See below for the list of possible values.  |
| Param Length  | int              | 4              | This is the length of the bianry data. |
| Param Data    | byte[]           | Param Length   | This is the data for the parameter. Length varies depending on the type. |

## Data Types
The following data types are supported by the protocol:

| Data Type  | ID | Size in Bytes | Notes |
| ---------- | -- | ------------- | ----- |
| BYTE       | 0  | 1             |       |
| SHORT      | 1  | 2             |       |
| INT        | 2  | 4             |       |
| LONG       | 3  | 8             |       |
| FLOAT      | 4  | 4             |       |
| DOUBLE     | 5  | 8             |       |
| BOOLEAN    | 6  | 1             | Byte. 0=false, 1=true |
| CHAR       | 7  | 2             | UTF-32 wide char |
| BINARY     | 8  | ...           | Varies in length |
| STRING     | 9  | ...           | UTF-32 wide string. Convenience type. Really just a binary type. |

# The Library
## Serialization
The library is very easy to use. First, you construct an MBPacket:
```java
MBPacket packet = new MBPacket();
```
You then fill it with various params.
```java
packet.addInt(40);
packet.addBinary(new byte[]{1, 2, 3, 4});
packet.addDouble(-32.5346);
```
After that, you're ready to turn it into a byte array!
```java
byte[] data = packet.getData();
```
From here, you can do whatever you want with the byte data. Send it across the internet, save it to disk, etc.
## Deserialization
Turning the data back into something meaningful is very simple as well!
Let's say you start with some binary data:
```java
byte[] data = //...
```
From here, **It is important to note**: the library assumes that the start byte and the first four bytes for the packet length
have been "consumed" already, presumably by whatever network library you're using. If it hasn't, then it is simple enough to do:
```java
data = Arrays.copyOfRange(data, ParamType.INT.getSizeInBytes() + 1, data.length);
```
The ```ParamType.INT.getSizeInBytes()``` does what you think it does. An integer contains 4 bytes. I don't think this will
change any time in the forseeable future, but is there for those who don't like magic numbers. The +1 is for the Packet Start.

You can now feed the data into the MBPacket constructor:
```java
MBPacket packet = new MBPacket(data);
```
All the deserialization is done in the constructor of MBPacket. From here, it is good practice to make sure the version is correct.
```java
if(!packet.isVersionValid()) {
    // error...
}
```
You can now grab the list of parameters:
```java
IMBParam[] params = packet.getParams();
```
You will need to cast each IMBParam into the type you want. Instead of having to use instanceof to verify the parameter
is the type you are expecting, The IMBParam class offers the `getType()` method.
```java
if(params[0].getType() == ParamType.INT) {
    int num = ((IntParam)params[0]).getInt();
}
```