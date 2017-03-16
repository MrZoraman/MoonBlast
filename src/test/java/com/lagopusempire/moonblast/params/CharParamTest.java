package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharParamTest {
    
    public CharParamTest() {
    }

    /**
     * Test of getChar method, of class CharParam.
     */
    @Test
    public void testGetChar() {
        CharParam p = new CharParam('j');
        assertEquals('j', p.getChar());
    }

    /**
     * Test of fillBuffer method, of class CharParam.
     */
    @Test
    public void testFillBuffer() {
        CharParam p = new CharParam('P');
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        CharParam p2 = new CharParam(b);
        assertEquals(p.getChar(), p2.getChar());
    }

    /**
     * Test of getType method, of class CharParam.
     */
    @Test
    public void testGetType() {
        CharParam p = new CharParam('d');
        assertEquals(ParamType.CHAR, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class CharParam.
     */
    @Test
    public void testGetSizeInBytes() {
        CharParam p = new CharParam('e');
        assertEquals(2, p.getSizeInBytes());
    }
}
