package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class CharParam implements IMBParam {
    private final char value;
    
    public CharParam(char value) {
        this.value = value;
    }
    
    public CharParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getChar();
    }
    
    public char getChar() {
        return value;
    }
    
    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putChar(value);
    }
    
    @Override
    public ParamType getType() {
        return ParamType.CHAR;
    }
    
    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
