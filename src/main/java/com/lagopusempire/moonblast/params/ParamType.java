package com.lagopusempire.moonblast.params;

public enum ParamType {
    BYTE    (0, Byte.SIZE / 8),
    SHORT   (1, Short.SIZE / 8),
    INT     (2, Integer.SIZE / 8),
    LONG    (3, Long.SIZE / 8),
    FLOAT   (4, Float.SIZE / 8),
    DOUBLE  (5, Double.SIZE / 8),
    BOOLEAN (6, 1),
    CHAR    (7, Character.SIZE / 8),
    BINARY  (8, -1),
    STRING  (9, -1);
    
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
    
    private ParamType(int value, int byteSize) {
        this.value = value;
        this.byteSize = byteSize;
    }

    public byte getValue() {
        return (byte) value;
    }
    
    public int getSizeInBytes() {
        return byteSize;
    }

    public static ParamType get(int value) {
        return TYPES[value];
    }
}
