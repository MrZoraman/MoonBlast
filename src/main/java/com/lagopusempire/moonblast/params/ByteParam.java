package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class ByteParam implements IMBParam {
    private final byte value;
    
    public ByteParam(byte value) {
        this.value = value;
    }
    
    public ByteParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.get();
    }
    
    public byte getByte() {
        return value;
    }
    
    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.put(value);
    }
    
    @Override
    public ParamType getType() {
        return ParamType.BYTE;
    }
    
    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
