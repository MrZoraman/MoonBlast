package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import com.lagopusempire.moonblast.params.IntParam;
import java.util.ArrayList;
import java.util.List;

public class IMBPacket {
    static final byte PACKET_START = '(';
    static final byte PACKET_END = ')';
    
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
        
        return null;
    }
    
    public void addIMBParam(IMBParam param) {
        params.add(param);
    }
    
    private int getPacketLengthInBytes() {
        return 0;
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
