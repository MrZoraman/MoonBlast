package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class BinaryParam implements IMBParam {
    private final byte[] value;
    
    public BinaryParam(byte[] value) {
        if(value == null) {
            throw new IllegalArgumentException("value cannot be null!");
        }
        
        this.value = value;
    }
    
    public BinaryParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        int length = buffer.getInt();
        this.value = new byte[length];
        buffer.get(value);
    }
    
    public byte[] getBinary() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putInt(value.length);
        buffer.put(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.BINARY;
    }

    @Override
    public int getSizeInBytes() {
        return value.length + ParamType.INT.getSizeInBytes();
    }
}
