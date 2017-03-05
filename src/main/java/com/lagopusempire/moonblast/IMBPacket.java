package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;

public interface IMBPacket {
    static final byte PACKET_START = '(';
    static final byte PACKET_END = ')';
    
    static final int VERSION = 1;
    
//    public IMBPacket addByte(byte value);
//    public IMBPacket addShort(short value);
//    public IMBPacket addInt(int value);
//    public IMBPacket addLong(long value);
//    public IMBPacket addFloat(float value);
//    public IMBPacket addDouble(double value);
//    public IMBPacket addBoolean(boolean value);
//    public IMBPacket addChar(char value);
//    
//    public IMBPacket addString(String value);
//    public IMBPacket addBinary(byte[] value);
//    
//    
//    public byte getByte(int index);
//    public short getShort(int index);
//    public int getInt(int index);
//    public long getLong(int index);
//    public float getFloat(int index);
//    public double getDouble(int index);
//    public boolean getBoolean(int index);
//    public char getChar(int index);
//    
//    public String getString(int index);
//    public byte[] getBinary(int index);
    
    public IMBParam[] getParams();
    
    public int getParamsLength();
    
    byte[] getData();
    
}
