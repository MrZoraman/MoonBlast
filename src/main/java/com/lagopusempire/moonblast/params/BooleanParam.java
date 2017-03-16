package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class BooleanParam implements IMBParam {
    private final boolean value;
    
    public BooleanParam(boolean value) {
        this.value = value;
    }
    
    public BooleanParam(ByteBuffer buffer) {
        this.value = buffer.get() != 0;
    }
    
    public boolean getBoolean() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        buffer.put((byte)(value ? 1 : 0));
    }

    @Override
    public ParamType getType() {
        return ParamType.BOOLEAN;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
