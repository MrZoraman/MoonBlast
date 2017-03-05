package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import com.lagopusempire.moonblast.params.IntParam;
import com.lagopusempire.moonblast.params.ParamType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class IMBPacket {
    static final byte PACKET_START = '(';
    static final byte PACKET_END = ')';
    
    private static final ParamType[] PACKET_LAYOUT = {
        ParamType.BYTE, //opening char
        ParamType.INT, //length (byte count)
        ParamType.INT, //version
        ParamType.INT, //number of params
        //... (various data)
        ParamType.BYTE //closing char
    };
    
    static final int VERSION = 1;
    
    private final List<IMBParam> params = new ArrayList<>();
    
    public IMBPacket() {
    }
    
    public IMBParam[] getParams() {
        return params.toArray(new IMBParam[0]);
    }
    
    public int getParamsLength() {
        return 0;
    }
    
    byte[] getData() {
        int packetLengthInBytes = getPacketLengthInBytes();
        ByteBuffer buffer = ByteBuffer.allocate(packetLengthInBytes);
        
        buffer.put(PACKET_START);
        buffer.putInt(packetLengthInBytes);
        buffer.putInt(VERSION);
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
            length += 1; //1 byte to store the existance of this param
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
}
