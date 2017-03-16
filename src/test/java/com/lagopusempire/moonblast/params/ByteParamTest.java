package com.lagopusempire.moonblast.params;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;


public class ByteParamTest {
    
    public ByteParamTest() {
    }

    /**
     * Test of getByte method, of class ByteParam.
     */
    @Test
    public void testGetByte() {
        ByteParam p = new ByteParam((byte)56);
        assertEquals((byte)56, p.getByte());
    }
    
    /**
     * Test of fillBuffer method, of class ByteParam.
     */
    @Test
    public void testFillBuffer() {
        ByteParam p = new ByteParam((byte)7);
        byte[] bytes = new byte[p.getSizeInBytes()];
        ByteBuffer b = ByteBuffer.wrap(bytes);
        p.fillBuffer(b);
        b.rewind();
        
        ByteParam p2 = new ByteParam(b);
        assertEquals(p.getByte(), p2.getByte());
    }

    /**
     * Test of getType method, of class ByteParam.
     */
    @Test
    public void testGetType() {
        ByteParam p = new ByteParam((byte)44);
        assertEquals(ParamType.BYTE, p.getType());
    }

    /**
     * Test of getSizeInBytes method, of class ByteParam.
     */
    @Test
    public void testGetSizeInBytes() {
        ByteParam p = new ByteParam((byte)-135);
        assertEquals(1, p.getSizeInBytes());
    }
    
}
