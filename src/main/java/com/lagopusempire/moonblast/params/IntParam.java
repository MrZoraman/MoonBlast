package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class IntParam implements IMBParam {
    private final int value;
    
    public IntParam(int value) {
        this.value = value;
    }
    
    public IntParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getInt();
    }
    
    public int getInt() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putInt(value);
    }
    
    @Override
    public ParamType getType() {
        return ParamType.INT;
    }
    
    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
