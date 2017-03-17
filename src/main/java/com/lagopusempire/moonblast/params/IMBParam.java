package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;

/**
 * Interface that all parameter types will implement.
 * @author Matt
 */
public interface IMBParam {
    /**
     * Fills a buffer with binary data.
     * @param buffer The buffer to be filled.
     */
    public void fillBuffer(ByteBuffer buffer);
    
    /**
     * Gets the param (data) type for this parameter. This is so just given
     * a simple IMBParam, the user can know the type without having to resort
     * to instanceof.
     * @return The type of the parameter, which is an indicator to what the
     * actual class instance is.
     */
    public ParamType getType();
    
    /**
     * Gets the size of this parameter in bytes.
     * @return The size in bytes. This will probably be an expected result
     * for statically sized types like ints or doubles, but is useful for
     * other types like binary blobs.
     */
    public int getSizeInBytes();
}
