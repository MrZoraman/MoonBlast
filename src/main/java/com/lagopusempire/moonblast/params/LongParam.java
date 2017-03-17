package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Long parameter.
 * @author Matt
 */
public class LongParam implements IMBParam {
    /**
     * The long data, either ready to be serialized or just deserialized.
     */
    private final long value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public LongParam(long value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public LongParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getLong();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The long data.
     */
    public long getLong() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
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
