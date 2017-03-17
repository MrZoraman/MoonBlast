package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringParam extends BinaryParam {
    private final String stringValue;
    
    public StringParam(String value) {
        super(value == null ? null : value.getBytes(StandardCharsets.UTF_16));
        
        this.stringValue = value;
    }
    
    public StringParam(ByteBuffer buffer) {
        super(buffer);
        
        this.stringValue = new String(value, StandardCharsets.UTF_16);
    }
    
    public String getString() {
        return stringValue;
    }
    
    @Override
    public ParamType getType() {
        return ParamType.STRING;
    }
}
