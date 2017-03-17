package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class FloatParam implements IMBParam {
    private final float value;
    
    public FloatParam(float value) {
        this.value = value;
    }
    
    public FloatParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getFloat();
    }
    
    public float getFloat() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putFloat(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.FLOAT;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
