package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Float parameter.
 * @author Matt
 */
public class FloatParam implements IMBParam {
    /**
     * The float data, either ready to be serialized or just deserialized.
     */
    private final float value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public FloatParam(float value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public FloatParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.getFloat();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The float data.
     */
    public float getFloat() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putFloat(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.FLOAT;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
