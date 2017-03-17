package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class DoubleParam implements IMBParam {
    private final double value;
    
    public DoubleParam(double value) {
        this.value = value;
    }
    
    public DoubleParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getDouble();
    }
    
    public double getDouble() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putDouble(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.DOUBLE;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
