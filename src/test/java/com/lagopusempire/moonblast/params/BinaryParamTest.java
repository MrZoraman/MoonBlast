package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryParamTest {
    
    public BinaryParamTest() {
    }
    
    /**
     * Test of getBinary method, of class BinaryParam.
     */
    @Test
    public void testGetInt() {
        BinaryParam p = new BinaryParam(new byte[]{1, 2, 3, 4});
        assertArrayEquals(new byte[]{1, 2, 3, 4}, p.getBinary());
    }

    /**
     * Test of fillBuffer method, of class BinaryParam.
     */
    @Test
    public void testFillBuffer() {
        BinaryParam p = new BinaryParam(new byte[]{5,-3,56,11});
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        BinaryParam p2 = new BinaryParam(b);
        assertArrayEquals(p.getBinary(), p2.getBinary());
    }

    /**
     * Test of getType method, of class BinaryParam.
     */
    @Test
    public void testGetType() {
        BinaryParam p = new BinaryParam(new byte[]{1, 2, 3});
        assertEquals(ParamType.BINARY, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class BinaryParam.
     */
    @Test
    public void testGetSizeInBytes() {
        BinaryParam p = new BinaryParam(new byte[]{1, 5, 88, 23});
        assertEquals(4 + ParamType.INT.getSizeInBytes(), p.getSizeInBytes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullBinaryParam() {
        byte[] b = null;
        BinaryParam p = new BinaryParam(b);
    }
}
