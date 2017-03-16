package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class LongParamTest {
    
    public LongParamTest() {
    }

    /**
     * Test of getLong method, of class LongParam.
     */
    @Test
    public void testGetLong() {
        LongParam p = new LongParam(245365436);
        assertEquals(245365436, p.getLong());
    }

    /**
     * Test of fillBuffer method, of class LongParam.
     */
    @Test
    public void testFillBuffer() {
        LongParam p = new LongParam(23556);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        LongParam p2 = new LongParam(b);
        assertEquals(p.getLong(), p2.getLong());
    }

    /**
     * Test of getType method, of class LongParam.
     */
    @Test
    public void testGetType() {
        LongParam p = new LongParam(-24444);
        assertEquals(ParamType.LONG, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class LongParam.
     */
    @Test
    public void testGetSizeInBytes() {
        LongParam p = new LongParam(3002);
        assertEquals(8, p.getSizeInBytes());
    }
    
}
