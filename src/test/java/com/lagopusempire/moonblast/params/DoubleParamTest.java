package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleParamTest {
    
    public DoubleParamTest() {
    }

    /**
     * Test of getDouble method, of class DoubleParam.
     */
    @Test
    public void testGetDouble() {
        DoubleParam p = new DoubleParam(23434.442);
        assertEquals((double)23434.442, p.getDouble(), 0.01);
    }

    /**
     * Test of fillBuffer method, of class DoubleParam.
     */
    @Test
    public void testFillBuffer() {
        DoubleParam p = new DoubleParam(222.555);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        DoubleParam p2 = new DoubleParam(b);
        assertEquals(p.getDouble(), p2.getDouble(), 0.01);
    }

    /**
     * Test of getType method, of class DoubleParam.
     */
    @Test
    public void testGetType() {
        DoubleParam p = new DoubleParam(22.32);
        assertEquals(ParamType.DOUBLE, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class DoubleParam.
     */
    @Test
    public void testGetSizeInBytes() {
        DoubleParam p = new DoubleParam(-22.355);
        assertEquals(8, p.getSizeInBytes());
    }
}
