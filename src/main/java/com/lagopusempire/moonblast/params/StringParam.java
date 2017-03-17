package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String parameter, which is really just an extension
 * of the binary parameter.
 * @author Matt
 */
public class StringParam extends BinaryParam {
    /**
     * The string data, either ready to be serialized or just deserialized.
     */
    private final String stringValue;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized. Cannot be null.
     */
    public StringParam(String value) {
        super(value == null ? null : value.getBytes(StandardCharsets.UTF_16));
        
        this.stringValue = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public StringParam(ByteBuffer buffer) {
        super(buffer);
        
        this.stringValue = new String(value, StandardCharsets.UTF_16);
    }
    
     /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The string data.
     */
    public String getString() {
        return stringValue;
    }
    
    @Override
    public ParamType getType() {
        return ParamType.STRING;
    }
}
