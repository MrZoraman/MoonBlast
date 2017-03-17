package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import java.nio.ByteBuffer;
import java.util.List;

@FunctionalInterface
public interface IDeserializer {
    public void deserialize(List<IMBParam> params, ByteBuffer buffer);
}
