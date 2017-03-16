package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class LongParam implements IMBParam {
    private final long value;
    
    public LongParam(long value) {
        this.value = value;
    }
    
    public LongParam(ByteBuffer buffer) {
        this.value = buffer.getLong();
    }
    
    public long getLong() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        buffer.putLong(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.LONG;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
    
}
