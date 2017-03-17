package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Byte parameter.
 * @author Matt
 */
public class ByteParam implements IMBParam {
    /**
     * The byte data, either ready to be serialized or just deserialized.
     */
    private final byte value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public ByteParam(byte value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public ByteParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        this.value = buffer.get();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The binary data.
     */
    public byte getByte() {
        return value;
    }
    
    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
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
