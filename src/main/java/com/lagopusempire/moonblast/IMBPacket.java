package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import com.lagopusempire.moonblast.params.IntParam;
import com.lagopusempire.moonblast.params.ParamType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMBPacket {
    private static final byte PACKET_START = '(';
    private static final byte PACKET_END = ')';
    private static final Map<ParamType, Deserializer> DESERIALIZERS = new HashMap<>();
    private static final VersionHandler VERSION_HANDLER = new VersionHandler();
    
    private static final ParamType[] PACKET_LAYOUT = {
        ParamType.BYTE, //opening char
        ParamType.INT, //length (byte count)
        ParamType.INT, //version
        ParamType.INT, //number of params
        //... (various data)
        ParamType.BYTE //closing char
    };
    
    @FunctionalInterface
    private interface Deserializer {
        public void deserialize(List<IMBParam> params, ByteBuffer buffer);
    }
    
    static
    {
        DESERIALIZERS.put(ParamType.INT, (params, buffer) -> params.add(new IntParam(buffer)));
    }
    
    
    private final List<IMBParam> params = new ArrayList<>();
    
    private boolean versionValid = true;
    
    public IMBPacket() { }
    
    public IMBPacket(byte[] data) {
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
        List<ParamType> paramTypes = new ArrayList<>();
        for(int ii = 0; ii < paramsLength; ii++) {
            byte typeValue = buffer.get();
            paramTypes.add(ParamType.get(typeValue));
        }
        
        for(int ii = 0; ii < paramTypes.size(); ii++) {
            ParamType type = paramTypes.get(ii);
            DESERIALIZERS.get(type).deserialize(params, buffer);
        }
        
        byte endByte = buffer.get();
        if(endByte != PACKET_END) {
            throw new PacketParseException("packet missing closing byte!");
        }
    }
    
    public IMBParam[] getParams() {
        return params.toArray(new IMBParam[0]);
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
    
//    public IMBPacket addByte(byte value);
//    public IMBPacket addShort(short value);
    public IMBPacket addInt(int value) {
        addIMBParam(new IntParam(value));
        return this;
    }
//    public IMBPacket addLong(long value);
//    public IMBPacket addFloat(float value);
//    public IMBPacket addDouble(double value);
//    public IMBPacket addBoolean(boolean value);
//    public IMBPacket addChar(char value);
//    public IMBPacket addBinary(byte[] value);
    
    private boolean isVersionValid() {
        return versionValid;
    }
}
