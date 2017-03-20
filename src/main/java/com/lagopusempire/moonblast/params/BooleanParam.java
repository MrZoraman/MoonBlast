package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.PacketParseException;
import java.nio.ByteBuffer;

/**
 * Boolean parameter.
 * @author Matt
 */
public class BooleanParam implements IMBParam {
    /**
     * The boolean data, either ready to be serialized or just deserialized.
     */
    private final boolean value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public BooleanParam(boolean value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public BooleanParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        this.value = buffer.get() != 0;
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The boolean data.
     */
    public boolean getBoolean() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        if(buffer.remaining() < ParamType.BYTE.getSizeInBytes()) {
            throw new PacketParseException("No bytes left for boolean!");
        }
        
        buffer.put((byte)(value ? 1 : 0));
    }

    @Override
    public ParamType getType() {
        return ParamType.BOOLEAN;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
