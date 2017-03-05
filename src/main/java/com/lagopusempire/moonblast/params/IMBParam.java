package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

public interface IMBParam {
    public void fillBuffer(ByteBuffer buffer);
    public ParamType getType();
    public int getSizeInBytes();
}
