package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanParamTest {
    
    public BooleanParamTest() {
    }

    /**
     * Test of getBoolean method, of class BooleanParam.
     */
    @Test
    public void testGetBoolean() {
        BooleanParam p = new BooleanParam(false);
        assertEquals(false, p.getBoolean());
    }

    /**
     * Test of fillBuffer method, of class BooleanParam.
     */
    @Test
    public void testFillBuffer() {
        BooleanParam p = new BooleanParam(true);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        BooleanParam p2 = new BooleanParam(b);
        assertEquals(p.getBoolean(), p2.getBoolean());
    }

    /**
     * Test of getType method, of class BooleanParam.
     */
    @Test
    public void testGetType() {
        BooleanParam p = new BooleanParam(false);
        assertEquals(ParamType.BOOLEAN, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class BooleanParam.
     */
    @Test
    public void testGetSizeInBytes() {
        BooleanParam p = new BooleanParam(true);
        assertEquals(1, p.getSizeInBytes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() {
        ByteBuffer b = null;
        BooleanParam p = new BooleanParam(b);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullFillBuffer() {
        BooleanParam p = new BooleanParam(false);
        p.fillBuffer(null);
    }
}
