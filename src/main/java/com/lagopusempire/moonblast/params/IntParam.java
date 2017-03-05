package com.lagopusempire.moonblast.params;

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
    public byte[] getData() {
        return null;
    }
}
