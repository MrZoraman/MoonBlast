package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Reads data from a byte buffer and turns it in to an IMBParam,
 * which is then added to a list of params.
 * @author Matt
 */
@FunctionalInterface
public interface IDeserializer {
    /**
     * Deserializes data from the ByteBuffer and adds it to the List of params.
     * @param params The list of params that the newly deserialized data should be added to.
     * @param buffer The binary data to be deserialized. 
     */
    public void deserialize(List<IMBParam> params, ByteBuffer buffer);
}
