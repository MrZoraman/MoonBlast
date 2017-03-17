package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Integer parameter.
 * @author Matt
 */
public class IntParam implements IMBParam {
    /**
     * The integer data, either ready to be serialized or just deserialized.
     */
    private final int value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public IntParam(int value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public IntParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getInt();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The integer data.
     */
    public int getInt() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
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
