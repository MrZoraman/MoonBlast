package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShortParamTest {
    
    public ShortParamTest() {
    }

    /**
     * Test of getShort method, of class ShortParam.
     */
    @Test
    public void testGetShort() {
        ShortParam p = new ShortParam((short)555);
        assertEquals((short)555, p.getShort());
    }

    /**
     * Test of fillBuffer method, of class ShortParam.
     */
    @Test
    public void testFillBuffer() {
        ShortParam p = new ShortParam((short)-4565);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        ShortParam p2 = new ShortParam(b);
        assertEquals(p.getShort(), p2.getShort());
    }

    /**
     * Test of getType method, of class ShortParam.
     */
    @Test
    public void testGetType() {
        ShortParam p = new ShortParam((short)245);
        assertEquals(ParamType.SHORT, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class ShortParam.
     */
    @Test
    public void testGetSizeInBytes() {
        ShortParam p = new ShortParam((short)-3222);
        assertEquals(2, p.getSizeInBytes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() {
        ByteBuffer b = null;
        ShortParam p = new ShortParam(b);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullFillBuffer() {
        ShortParam p = new ShortParam((short)2456);
        p.fillBuffer(null);
    }
}
