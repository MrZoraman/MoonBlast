package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringParamTest {
    
    public StringParamTest() {
    }

    /**
     * Test of getString method, of class StringParam.
     */
    @Test
    public void testGetString() {
        StringParam p = new StringParam("Hey there");
        assertEquals("Hey there", p.getString());
    }
    
    /**
     * Test of fillBuffer method, of class BinaryParam.
     */
    @Test
    public void testFillBuffer() {
        StringParam p = new StringParam("good stuff");
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        StringParam p2 = new StringParam(b);
        assertEquals(p.getString(), p2.getString());
    }

    /**
     * Test of getType method, of class StringParam.
     */
    @Test
    public void testGetType() {
        StringParam p = new StringParam("asdf");
        assertEquals(ParamType.STRING, p.getType());
    }
    
    /**
     * Test of getSizeInBytes method, of class BinaryParam.
     */
    @Test
    public void testGetSizeInBytes() {
        StringParam p = new StringParam("hello there~");
        assertEquals(("hello there~".length() * 2) + ParamType.INT.getSizeInBytes() + 2, p.getSizeInBytes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullBinaryConstructor() {
        String s = null;
        StringParam p = new StringParam(s);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() {
        ByteBuffer b = null;
        StringParam p = new StringParam(b);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullFillBuffer() {
        StringParam p = new StringParam("goodnight.");
        p.fillBuffer(null);
    }
}
