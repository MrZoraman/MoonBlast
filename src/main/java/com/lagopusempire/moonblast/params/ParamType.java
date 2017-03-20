package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.IDeserializer;

/**
 * Contains a list of parameter types, as well as serializers, which
 * can be accessed in O(1) time, no fancy data structures required.
 * @author Matt
 */
public enum ParamType {
    BYTE    (0, Byte.SIZE / 8,      (params, buffer) -> params.add(new ByteParam(buffer))),
    SHORT   (1, Short.SIZE / 8,     (params, buffer) -> params.add(new ShortParam(buffer))),
    INT     (2, Integer.SIZE / 8,   (params, buffer) -> params.add(new IntParam(buffer))),
    LONG    (3, Long.SIZE / 8,      (params, buffer) -> params.add(new LongParam(buffer))),
    FLOAT   (4, Float.SIZE / 8,     (params, buffer) -> params.add(new FloatParam(buffer))),
    DOUBLE  (5, Double.SIZE / 8,    (params, buffer) -> params.add(new DoubleParam(buffer))),
    BOOLEAN (6, 1,                  (params, buffer) -> params.add(new BooleanParam(buffer))),
    CHAR    (7, Character.SIZE / 8, (params, buffer) -> params.add(new CharParam(buffer))),
    BINARY  (8, -1,                 (params, buffer) -> params.add(new BinaryParam(buffer))),
    STRING  (9, -1,                 (params, buffer) -> params.add(new StringParam(buffer)));
    
    /**
     * This is the first index that is out of bounds for the param types.
     * This will be the index of the last valid type + 1.
     */
    private static final int PARAMTYPE_EOF = 10;
    
    /**
     * This is an array of types. These indexes must coorespond
     * with the first parameters of the ParamTypes specified in
     * the enum. Basically, keep this array and the list of enum
     * values in the same order.
     */
    private static final ParamType[] TYPES = {
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        CHAR,
        BINARY,
        STRING
    };
    
    /**
     * Checks if a param type byte value maps to a valid type or not.
     * @param value The value to test.
     * @return True if it is valid, false if it is out of bounds.
     */
    public static boolean isValid(byte value) {
        return value >= 0 && value < PARAMTYPE_EOF;
    }
    
    /**
     * The ordinal value of this data type in the type array.
     */
    final int value;
    
    /**
     * The size of this data type in bytes.
     */
    final int byteSize;
    
    /**
     * The deserializer for this data type. It might be better to think
     * of this as a deserializer factory.
     */
    private final IDeserializer deserializer;
    
    /**
     * Creates a ParamType.
     * @param value The ordinal value it shows up as in the array/enum listing.
     * @param byteSize The size of this type in bytes.
     * @param deserializer The deserializer for this type.
     */
    private ParamType(int value, int byteSize, IDeserializer deserializer) {
        this.value = value;
        this.byteSize = byteSize;
        this.deserializer = deserializer;
    }

    /**
     * Gets the ordinal value for this data type.
     * @return The value as a byte, ready to be put in a packet.
     */
    public byte getValue() {
        return (byte) value;
    }
    
    /**
     * Gets the size of this data type in bytes.
     * @return Size in bytes. Probably intuitive for some data types,
     * but meaningless for other data types, like binary or string,
     * which will return -1 instead.
     */
    public int getSizeInBytes() {
        return byteSize;
    }
    
    /**
     * Gets the deserializer for this data type.
     * @return The deserializer instance, of which one will exist
     * for each data type.
     */
    public IDeserializer getDeserializer() {
        return deserializer;
    }

    /**
     * Converts an ordinal value to its corresponding data
     * type in O(1) time (it just accesses an array, so make
     * sure the value is less than the amount of data types
     * supported by this implementation, or you will get an
     * IndexOutOfBounds exception.
     * @param value The ordinal value.
     * @return The data type enum instance.
     */
    public static ParamType get(int value) {
        return TYPES[value];
    }
}
