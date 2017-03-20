package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.PacketParseException;
import java.nio.ByteBuffer;

/**
 * Short parameter.
 * @author Matt
 */
public class ShortParam implements IMBParam {
    /**
     * The short data, either ready to be serialized or just deserialized.
     */
    private final short value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public ShortParam(short value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public ShortParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        this.value = buffer.getShort();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The short data.
     */
    public short getShort() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        if(buffer.remaining() < ParamType.SHORT.getSizeInBytes()) {
            throw new PacketParseException("No bytes left for short!");
        }
        
        buffer.putShort(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.SHORT;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
