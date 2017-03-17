package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntParamTest {
    
    public IntParamTest() {
    }

    /**
     * Test of getInt method, of class IntParam.
     */
    @Test
    public void testGetInt() {
        IntParam p = new IntParam(24);
        assertEquals(24, p.getInt());
    }
    
    /**
     * Test of fillBuffer method, of class IntParam.
     */
    @Test
    public void testFillBuffer() {
        IntParam p = new IntParam(5);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        IntParam p2 = new IntParam(b);
        assertEquals(p.getInt(), p2.getInt());
    }

    /**
     * Test of getType method, of class IntParam.
     */
    @Test
    public void testGetType() {
        IntParam p = new IntParam(25);
        assertEquals(ParamType.INT, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class IntParam.
     */
    @Test
    public void testGetSizeInBytes() {
        IntParam p = new IntParam(-252);
        assertEquals(4, p.getSizeInBytes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() {
        ByteBuffer b = null;
        IntParam p = new IntParam(b);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullFillBuffer() {
        IntParam p = new IntParam(22345);
        p.fillBuffer(null);
    }
}
