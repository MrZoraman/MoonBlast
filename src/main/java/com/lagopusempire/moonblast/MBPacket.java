package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * The most basic component in the MoonBlast library. This contains 
 * data itself, as well as the means of serializing and deserializing it.
 * @author Matt
 */
public class MBPacket {
    /**
     * The end byte
     */
    private static final byte PACKET_START = '(';
    
    /**
     * The end byte
     */
    private static final byte PACKET_END = ')';
    
    /**
     * The version handler. This contains what could be complex code for
     * determining if a packet's version is compatible with this library's
     * version or not.
     */
    private static final VersionHandler VERSION_HANDLER = new VersionHandler();
    
    /**
     * This is the layout of the packet. This array of param types is used
     * for determining how big the packet will be. This will not change, but
     * is here for the sake of avoiding magic numbers.
     */
    private static final ParamType[] PACKET_LAYOUT = {
        ParamType.BYTE, //opening char
        ParamType.INT, //length (byte count)
        ParamType.BYTE, //version
        ParamType.INT, //number of params
        //... (various data)
        ParamType.BYTE //closing char
    };
    
    /**
     * This is the minimum size the packet can be in bytes. It won't contain
     * any data.
     */
    private static final int MIN_SIZE_IN_BYTES;
    
    static
    {
        int minSize = 0;
        //start at 2 to skip the first two fields, which should be read
        //before the a packet is constructed from a byte array.
        for(int ii = 2; ii < PACKET_LAYOUT.length; ii++) {
            minSize += PACKET_LAYOUT[ii].getSizeInBytes();
        }
        MIN_SIZE_IN_BYTES = minSize;
    }
    
    /**
     * This is the list of parameters that make up the packet. It will either
     * be a list of parameters just deserialized, or a list of parameters about
     * to be serialized.
     */
    private final List<IMBParam> params = new LinkedList<>();
    
    /**
     * Whether or not this packet is compatible with this MoonBlast
     * implementation or not.
     */
    private boolean versionValid = true;
    
    /**
     * Constructor used for when data is to be serialized.
     */
    public MBPacket() { }
    
    /**
     * Constructor used for when data is to be deserialized.
     * @param data The binary data to be deserialized. Note that the binary
     * data should not contain the start character or the beginning four bytes
     * for the length of the packet.
     */
    public MBPacket(byte[] data) {
        //when this method is called, the start char and the length of packet
        //will have already been read. Length of packet is built into
        //data.length
        if(data == null) {
            throw new IllegalArgumentException("data cannot be null!");
        }
        
        if(data.length < MIN_SIZE_IN_BYTES) {
            throw new PacketParseException("data is not long enough to contain all of the packet metadata!");
        }
        
        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte version = buffer.get();
        
        if(!VERSION_HANDLER.isVersionValid(version)) {
            versionValid = false;
            return;
        }
        
        int paramsLength = buffer.getInt();
        for(int ii = 0; ii < paramsLength; ii++) {
            byte typeValue = buffer.get();
            if(!ParamType.isValid(typeValue)) {
                throw new PacketParseException("Invalid data type read: " + typeValue);
            }
            
            ParamType type = ParamType.get(typeValue);
            type.getDeserializer().deserialize(params, buffer);
        }
        
        byte endByte = buffer.get();
        if(endByte != PACKET_END) {
            throw new PacketParseException("Packet missing closing byte!");
        }
    }
    
    /**
     * Gets a list of parameters.
     * @return An array of parameters. This array can be tweaked, it shouldn't
     * mess with the internal state of this packet.
     */
    public IMBParam[] getParams() {
        return params.stream().toArray(IMBParam[]::new);
    }
    
    /**
     * Deserializes the data in this packet and returns it as a byte array.
     * @return The binary array which can be used wherever.
     */
    public byte[] getData() {
        int packetLengthInBytes = getPacketLengthInBytes();
        ByteBuffer buffer = ByteBuffer.allocate(packetLengthInBytes);
        
        buffer.put(PACKET_START);
        buffer.putInt(packetLengthInBytes);
        buffer.put(VERSION_HANDLER.getVersion());
        buffer.putInt(params.size());
        
        for(int ii = 0; ii < params.size(); ii++) {
            IMBParam param = params.get(ii);
            buffer.put(param.getType().getValue());
            param.fillBuffer(buffer);
        }
        
        buffer.put(PACKET_END);
        
        return buffer.array();
    }
    
    /**
     * Adds a parameter to the packet.
     * @param param An IMBParam. You might want to also take a look
     * at the various convenience methods included in this class that will
     * automatically wrap the native data types for you.
     */
    public void addIMBParam(IMBParam param) {
        params.add(param);
    }
    
    /**
     * Gets the length of the packet. This needs to be calculated ahead of time
     * for writing bytes 1-5 of the packet.
     * @return The length of the packet in bytes.
     */
    public int getPacketLengthInBytes() {
        int length = 0;
        for(ParamType type : PACKET_LAYOUT) {
            length += type.getSizeInBytes();
        }
        
        for(IMBParam param : params) {
            length += 1; //1 byte to store the existence of this param
            length += param.getSizeInBytes();
        }
        
        return length;
    }
    
    /**
     * Adds a byte to the list of parameters.
     * @param value The byte to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addByte(byte value) {
        addIMBParam(new ByteParam(value));
        return this;
    }
    
    /**
     * Adds a short to the list of parameters.
     * @param value The short to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addShort(short value) {
        addIMBParam(new ShortParam(value));
        return this;
    }
    
    /**
     * Adds an int to the list of parameters.
     * @param value The int to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addInt(int value) {
        addIMBParam(new IntParam(value));
        return this;
    }
    
    /**
     * Adds a long to the list of parameters.
     * @param value The long to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addLong(long value) {
        addIMBParam(new LongParam(value));
        return this;
    }
    
    /**
     * Adds a float to the list of parameters.
     * @param value The float to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addFloat(float value) {
        addIMBParam(new FloatParam(value));
        return this;
    }
    
    /**
     * Adds a double to the list of parameters.
     * @param value The double to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addDouble(double value) {
        addIMBParam(new DoubleParam(value));
        return this;
    }
    
    /**
     * Adds a boolean to the list of parameters.
     * @param value The boolean to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addBoolean(boolean value) {
        addIMBParam(new BooleanParam(value));
        return this;
    }
    
    /**
     * Adds a char to the list of parameters.
     * @param value The char to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addChar(char value) {
        addIMBParam(new CharParam(value));
        return this;
    }
    
    /**
     * Adds a binary blob to the list of parameters.
     * @param value The binary blob to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addBinary(byte[] value) {
        addIMBParam(new BinaryParam(value));
        return this;
    }
    
    /**
     * Adds a string to the list of parameters.
     * @param value The string to add.
     * @return The same instance. This is used for method chaining.
     */
    public MBPacket addString(String value) {
        addIMBParam(new StringParam(value));
        return this;
    }
    
    /**
     * Checks if the version is valid or not. This should be checked after
     * the packet has been deserialized before trying to use the data. Otherwise
     * your parameter list will be empty.
     * @return True if the packet is compatible with this library version,
     * false if otherwise.
     */
    public boolean isVersionValid() {
        return versionValid;
    }
}
