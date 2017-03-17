package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.*;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class MBPacketTest {
    
    public MBPacketTest() {
    }
    
    @Test
    public void testPacketLength() {
        MBPacket packet = new MBPacket();
        assertEquals(1 + 4 + 4 + 4 + 1, packet.getPacketLengthInBytes());
    }
    
    @Test
    public void testPacketLengthWithParams() {
        MBPacket packet = new MBPacket();
        packet.addInt(57);
        packet.addInt(632);
        packet.addInt(-4);
        assertEquals(1 + 4 + 4 + 4 + (1 * 3) + (4 + 4 + 4) + 1, packet.getPacketLengthInBytes());
    }
    
    @Test
    public void testPacketIntDeserialization() {
        byte[] data = new byte[] {
            0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, -117, 39, 41 
        };
        
        MBPacket packet = new MBPacket(data);
        assertTrue(packet.isVersionValid());
        IMBParam[] params = packet.getParams();
        assertEquals(2, params.length);
        
        IntParam p0 = (IntParam)params[0];
        IntParam p1 = (IntParam)params[1];
        
        assertEquals(0, p0.getInt());
        assertEquals(35623, p1.getInt());
    }
    
    @Test
    public void testInvalidVersion() {
        byte[] data = new byte[] {
            0, 42, 0, 1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, -117, 39, 41 
        };
        
        MBPacket packet = new MBPacket(data);
        assertFalse(packet.isVersionValid());
    }
    
    @Test
    public void testParamsAndBinary() {
        MBPacket packet = new MBPacket();
        packet.addInt(5)
                .addInt(10)
                .addInt(15)
                .addBinary(new byte[]{1, 2, 3, 4});
        byte[] b = packet.getData();
        
        byte[] b2 = Arrays.copyOfRange(b, ParamType.INT.getSizeInBytes() + 1, b.length);
        
        packet = new MBPacket(b2);
        IMBParam[] params = packet.getParams();
        assertEquals(4, params.length);
        assertEquals(ParamType.INT, params[0].getType());
        assertEquals(5, ((IntParam)params[0]).getInt());
        assertEquals(ParamType.INT, params[1].getType());
        assertEquals(10, ((IntParam)params[1]).getInt());
        assertEquals(ParamType.INT, params[2].getType());
        assertEquals(15, ((IntParam)params[2]).getInt());
        
        assertEquals(ParamType.BINARY, params[3].getType());
        assertArrayEquals(new byte[]{1, 2, 3, 4}, ((BinaryParam)params[3]).getBinary());
    }
    
    @Test
    public void testBinaryParamsEverywhere() {
        MBPacket packet = new MBPacket();
        packet.addInt(10)
                .addBinary(new byte[]{1, 2, 3})
                .addInt(15)
                .addBinary(new byte[]{4, 5, 6})
                .addBinary(new byte[]{7, 8, 9})
                .addInt(20);
        
        byte[] b = packet.getData();
        
        byte[] b2 = Arrays.copyOfRange(b, ParamType.INT.getSizeInBytes() + 1, b.length);
        
        packet = new MBPacket(b2);
        IMBParam[] params = packet.getParams();
        assertEquals(6, params.length);
        assertEquals(ParamType.INT, params[0].getType());
        assertEquals(ParamType.BINARY, params[1].getType());
        assertEquals(ParamType.INT, params[2].getType());
        assertEquals(ParamType.BINARY, params[3].getType());
        assertEquals(ParamType.BINARY, params[4].getType());
        assertEquals(ParamType.INT, params[5].getType());
        
        assertEquals(10, ((IntParam)params[0]).getInt());
        assertEquals(15, ((IntParam)params[2]).getInt());
        assertEquals(20, ((IntParam)params[5]).getInt());
        
        assertArrayEquals(new byte[]{1, 2, 3}, ((BinaryParam)params[1]).getBinary());
        assertArrayEquals(new byte[]{4, 5, 6}, ((BinaryParam)params[3]).getBinary());
        assertArrayEquals(new byte[]{7, 8, 9}, ((BinaryParam)params[4]).getBinary());
    }
    
    @Test
    public void theBigTest() {
        MBPacket packet = new MBPacket();
        packet.addByte((byte)-53)
                .addShort((short)25560)
                .addInt(245667000)
                .addLong(2020506506L)
                .addFloat(432.53f)
                .addDouble(222.45560600)
                .addBoolean(false)
                .addChar('j')
                .addBinary(new byte[]{1, -4, 2, 33})
                .addString("testing 123");
        byte[] b = packet.getData();
        byte[] b2 = Arrays.copyOfRange(b, ParamType.INT.getSizeInBytes() + 1, b.length);
        
        packet = new MBPacket(b2);
        IMBParam[] params = packet.getParams();
        assertEquals(10, params.length);
        
        assertEquals(ParamType.BYTE, params[0].getType());
        assertEquals(ParamType.SHORT, params[1].getType());
        assertEquals(ParamType.INT, params[2].getType());
        assertEquals(ParamType.LONG, params[3].getType());
        assertEquals(ParamType.FLOAT, params[4].getType());
        assertEquals(ParamType.DOUBLE, params[5].getType());
        assertEquals(ParamType.BOOLEAN, params[6].getType());
        assertEquals(ParamType.CHAR, params[7].getType());
        assertEquals(ParamType.BINARY, params[8].getType());
        assertEquals(ParamType.STRING, params[9].getType());
        
        assertEquals((byte)-53, ((ByteParam)params[0]).getByte());
        assertEquals((short)25560, ((ShortParam)params[1]).getShort());
        assertEquals(245667000, ((IntParam)params[2]).getInt());
        assertEquals(2020506506L, ((LongParam)params[3]).getLong());
        assertEquals(432.53f, ((FloatParam)params[4]).getFloat(), 0.01);
        assertEquals(222.45560600, ((DoubleParam)params[5]).getDouble(), 0.01);
        assertEquals(false, ((BooleanParam)params[6]).getBoolean());
        assertEquals('j', ((CharParam)params[7]).getChar());
        assertArrayEquals(new byte[]{1, -4, 2, 33}, ((BinaryParam)params[8]).getBinary());
        assertEquals("testing 123", ((StringParam)params[9]).getString());
    }
}
