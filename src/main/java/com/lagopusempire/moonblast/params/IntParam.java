package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class IntParam implements IMBParam {
    private final int value;
    
    public IntParam(int value) {
        this.value = value;
    }
    
    public int getInt() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
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
