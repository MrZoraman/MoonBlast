package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class ShortParam implements IMBParam {
    private final short value;
    
    public ShortParam(short value) {
        this.value = value;
    }
    
    public ShortParam(ByteBuffer buffer) {
        this.value = buffer.getShort();
    }
    
    public short getShort() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        buffer.putShort(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.SHORT;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
