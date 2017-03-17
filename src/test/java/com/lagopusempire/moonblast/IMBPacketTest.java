package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.BinaryParam;
import com.lagopusempire.moonblast.params.IMBParam;
import com.lagopusempire.moonblast.params.IntParam;
import com.lagopusempire.moonblast.params.ParamType;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class IMBPacketTest {
    
    public IMBPacketTest() {
    }
    
    @Test
    public void testPacketLength() {
        IMBPacket packet = new IMBPacket();
        assertEquals(1 + 4 + 4 + 4 + 1, packet.getPacketLengthInBytes());
    }
    
    @Test
    public void testPacketLengthWithParams() {
        IMBPacket packet = new IMBPacket();
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
        
        IMBPacket packet = new IMBPacket(data);
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
        
        IMBPacket packet = new IMBPacket(data);
        assertFalse(packet.isVersionValid());
    }
    
    @Test
    public void testParamsAndBinary() {
        IMBPacket packet = new IMBPacket();
        packet.addInt(5)
                .addInt(10)
                .addInt(15)
                .addBinary(new byte[]{1, 2, 3, 4});
        byte[] b = packet.getData();
        
        byte[] b2 = Arrays.copyOfRange(b, ParamType.INT.getSizeInBytes() + 1, b.length);
        
        packet = new IMBPacket(b2);
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
        IMBPacket packet = new IMBPacket();
        packet.addInt(10)
                .addBinary(new byte[]{1, 2, 3})
                .addInt(15)
                .addBinary(new byte[]{4, 5, 6})
                .addBinary(new byte[]{7, 8, 9})
                .addInt(20);
        
        byte[] b = packet.getData();
        
        byte[] b2 = Arrays.copyOfRange(b, ParamType.INT.getSizeInBytes() + 1, b.length);
        
        packet = new IMBPacket(b2);
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
}
