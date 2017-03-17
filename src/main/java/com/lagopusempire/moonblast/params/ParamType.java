package com.lagopusempire.moonblast.params;

import com.lagopusempire.moonblast.IDeserializer;

public enum ParamType {
    BYTE    (0, Byte.SIZE / 8, (params, buffer) -> params.add(new ByteParam(buffer))),
    SHORT   (1, Short.SIZE / 8, (params, buffer) -> params.add(new ShortParam(buffer))),
    INT     (2, Integer.SIZE / 8, (params, buffer) -> params.add(new IntParam(buffer))),
    LONG    (3, Long.SIZE / 8, (params, buffer) -> params.add(new LongParam(buffer))),
    FLOAT   (4, Float.SIZE / 8, (params, buffer) -> params.add(new FloatParam(buffer))),
    DOUBLE  (5, Double.SIZE / 8, (params, buffer) -> params.add(new DoubleParam(buffer))),
    BOOLEAN (6, 1, (params, buffer) -> params.add(new BooleanParam(buffer))),
    CHAR    (7, Character.SIZE / 8, (params, buffer) -> params.add(new CharParam(buffer))),
    BINARY  (8, -1, (params, buffer) -> params.add(new BinaryParam(buffer))),
    STRING  (9, -1, (params, buffer) -> params.add(new StringParam(buffer)));
    
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
    
    final int value;
    final int byteSize;
    private final IDeserializer deserializer;
    
    private ParamType(int value, int byteSize, IDeserializer deserializer) {
        this.value = value;
        this.byteSize = byteSize;
        this.deserializer = deserializer;
    }

    public byte getValue() {
        return (byte) value;
    }
    
    public int getSizeInBytes() {
        return byteSize;
    }
    
    public IDeserializer getDeserializer() {
        return deserializer;
    }

    public static ParamType get(int value) {
        return TYPES[value];
    }
}
