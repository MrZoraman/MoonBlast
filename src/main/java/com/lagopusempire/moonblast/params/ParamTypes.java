package com.lagopusempire.moonblast.params;

public enum ParamTypes {
    
    BYTE(0),
    SHORT(1),
    INT(2),
    LONG(3),
    FLOAT(4),
    DOUBLE(5),
    BOOLEAN(6),
    CHAR(7),
    STRING(8),
    BINARY(9);
    
    private static final ParamTypes[] TYPES = {
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        CHAR,
        STRING,
        BINARY
    };
    
    
    int value;
    
    private ParamTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public ParamTypes get(int value) {
        return TYPES[value];
    }
}
