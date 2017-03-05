package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public class IntParam implements IMBParam {
    private int value;
    
    public IntParam(int value) {
        this.value = value;
    }
    
    public IntParam() {
        
    }
    
    public int getInt() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer, int index) {
        buffer.putInt(index, value);
    }
    
}
