package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class FloatParam implements IMBParam {
    private final float value;
    
    public FloatParam(float value) {
        this.value = value;
    }
    
    public FloatParam(ByteBuffer buffer) {
        this.value = buffer.getFloat();
    }
    
    public float getFloat() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
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
