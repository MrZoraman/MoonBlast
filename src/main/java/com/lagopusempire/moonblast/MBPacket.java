package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class MBPacket {
    private static final byte PACKET_START = '(';
    private static final byte PACKET_END = ')';
    private static final VersionHandler VERSION_HANDLER = new VersionHandler();
    
    private static final ParamType[] PACKET_LAYOUT = {
        ParamType.BYTE, //opening char
        ParamType.INT, //length (byte count)
        ParamType.INT, //version
        ParamType.INT, //number of params
        //... (various data)
        ParamType.BYTE //closing char
    };
    
    private final List<IMBParam> params = new LinkedList<>();
    
    private boolean versionValid = true;
    
    public MBPacket() { }
    
    public MBPacket(byte[] data) {
        //when this method is called, the start char and the length of packet
        //will have already been read. Length of packet is built into
        //data.length
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int version = buffer.getInt();
        
        if(!VERSION_HANDLER.isVersionValid(version)) {
            versionValid = false;
            return;
        }
        
        int paramsLength = buffer.getInt();
        ParamType[] paramTypes = new ParamType[paramsLength];
        for(int ii = 0; ii < paramsLength; ii++) {
            byte typeValue = buffer.get();
            paramTypes[ii] = ParamType.get(typeValue);
        }
        
        for(int ii = 0; ii < paramsLength; ii++) {
            paramTypes[ii].getDeserializer().deserialize(params, buffer);
        }
        
        byte endByte = buffer.get();
        if(endByte != PACKET_END) {
            throw new PacketParseException("packet missing closing byte!");
        }
    }
    
    public IMBParam[] getParams() {
        return params.stream().toArray(IMBParam[]::new);
    }
    
    public byte[] getData() {
        int packetLengthInBytes = getPacketLengthInBytes();
        ByteBuffer buffer = ByteBuffer.allocate(packetLengthInBytes);
        
        buffer.put(PACKET_START);
        buffer.putInt(packetLengthInBytes);
        buffer.putInt(VERSION_HANDLER.getVersion());
        buffer.putInt(params.size());
        
        for(int ii = 0; ii < params.size(); ii++) {
            buffer.put(params.get(ii).getType().getValue());
        }
        
        for(int ii = 0; ii < params.size(); ii++) {
            params.get(ii).fillBuffer(buffer);
        }
        
        buffer.put(PACKET_END);
        
        return buffer.array();
    }
    
    public void addIMBParam(IMBParam param) {
        params.add(param);
    }
    
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
    
    public MBPacket addByte(byte value) {
        addIMBParam(new ByteParam(value));
        return this;
    }
    
    public MBPacket addShort(short value) {
        addIMBParam(new ShortParam(value));
        return this;
    }
    
    public MBPacket addInt(int value) {
        addIMBParam(new IntParam(value));
        return this;
    }
    
    public MBPacket addLong(long value) {
        addIMBParam(new LongParam(value));
        return this;
    }
    
    public MBPacket addFloat(float value) {
        addIMBParam(new FloatParam(value));
        return this;
    }
    
    public MBPacket addDouble(double value) {
        addIMBParam(new DoubleParam(value));
        return this;
    }
    
    public MBPacket addBoolean(boolean value) {
        addIMBParam(new BooleanParam(value));
        return this;
    }
    
    public MBPacket addChar(char value) {
        addIMBParam(new CharParam(value));
        return this;
    }
    
    public MBPacket addBinary(byte[] value) {
        addIMBParam(new BinaryParam(value));
        return this;
    }
    
    public MBPacket addString(String value) {
        addIMBParam(new StringParam(value));
        return this;
    }
    
    public boolean isVersionValid() {
        return versionValid;
    }
}
