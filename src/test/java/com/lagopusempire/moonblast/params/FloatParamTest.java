package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class FloatParamTest {
    
    public FloatParamTest() {
    }

    /**
     * Test of getFloat method, of class FloatParam.
     */
    @Test
    public void testGetFloat() {
        FloatParam p = new FloatParam((float)242.546);
        assertEquals((float)242.546, p.getFloat(), 0.01);
    }

    /**
     * Test of fillBuffer method, of class FloatParam.
     */
    @Test
    public void testFillBuffer() {
        FloatParam p = new FloatParam((float)66.34321);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        FloatParam p2 = new FloatParam(b);
        assertEquals(p.getFloat(), p2.getFloat(), 0.01);
    }

    /**
     * Test of getType method, of class FloatParam.
     */
    @Test
    public void testGetType() {
        FloatParam p = new FloatParam((float)-442.221);
        assertEquals(ParamType.FLOAT, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class FloatParam.
     */
    @Test
    public void testGetSizeInBytes() {
        FloatParam p = new FloatParam((float)-234.2467);
        assertEquals(4, p.getSizeInBytes());
    }
}
