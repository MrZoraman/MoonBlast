package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.PacketParseException;
import java.nio.ByteBuffer;

/**
 * Character parameter.
 * @author Matt
 */
public class CharParam implements IMBParam {
    /**
     * The character data, either ready to be serialized or just deserialized.
     */
    private final char value;
    
    /**
     * Constructor for serializing.
     * @param value The data to be serialized.
     */
    public CharParam(char value) {
        this.value = value;
    }
    
    /**
     * Constructor for deserializing. Data will be read from the buffer.
     * @param buffer The buffer to be read from.
     */
    public CharParam(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        if(buffer.remaining() < ParamType.CHAR.getSizeInBytes()) {
            throw new PacketParseException("No bytes left for char!");
        }
        
        this.value = buffer.getChar();
    }
    
    /**
     * Gets the data held by this instance. Will likely be used to read
     * data after its been deserialized.
     * @return The character data.
     */
    public char getChar() {
        return value;
    }
    
    @Override
    public void fillBuffer(ByteBuffer buffer) {
        if(buffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null!");
        }
        
        buffer.putChar(value);
    }
    
    @Override
    public ParamType getType() {
        return ParamType.CHAR;
    }
    
    @Override
    public int getSizeInBytes() {
        return getType().getSizeInBytes();
    }
}
