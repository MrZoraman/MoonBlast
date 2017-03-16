package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class ByteParam implements IMBParam {
    private final byte value;
    
    public ByteParam(byte value) {
        this.value = value;
    }
    
    public ByteParam(ByteBuffer buffer) {
        this.value = buffer.get();
    }
    
    public byte getByte() {
        return value;
    }
    
    @Override
    public void fillBuffer(ByteBuffer buffer) {
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
