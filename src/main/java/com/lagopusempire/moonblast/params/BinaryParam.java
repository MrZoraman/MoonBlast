package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Binary parameter.
 * @author Matt
 */
public class BinaryParam implements IMBParam {
    /**
     * The binary data, either ready to be serialized or just deserialized.
     */
    protected final byte[] value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized. Cannot be null.
     */
    public BinaryParam(byte[] value) {
        if(value == null) {
            throw new IllegalArgumentException("value cannot be null!");
        }
        
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public BinaryParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        int length = buffer.getInt();
        this.value = new byte[length];
        buffer.get(value);
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The binary data.
     */
    public byte[] getBinary() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("buffer cannot be null!");
        }
        
        buffer.putInt(value.length);
        buffer.put(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.BINARY;
    }

    @Override
    public int getSizeInBytes() {
        return value.length + ParamType.INT.getSizeInBytes();
    }
}
