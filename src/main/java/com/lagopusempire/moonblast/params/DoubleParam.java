package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.PacketParseException;
import java.nio.ByteBuffer;

/**
 * Double parameter.
 * @author Matt
 */
public class DoubleParam implements IMBParam {
    /**
     * The double data, either ready to be serialized or just deserialized.
     */
    private final double value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public DoubleParam(double value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public DoubleParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        if(buffer.remaining() < ParamType.DOUBLE.getSizeInBytes()) {
            throw new PacketParseException("No bytes left for double!");
        }
        
        this.value = buffer.getDouble();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The double data.
     */
    public double getDouble() {
        return value;
    }

    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        buffer.putDouble(value);
    }

    @Override
    public ParamType getType() {
        return ParamType.DOUBLE;
    }

    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
