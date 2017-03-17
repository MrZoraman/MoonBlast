package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;
import com.lagopusempire.moonblast.params.IntParam;
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
        IMBParam[] params = packet.getParams();
        assertEquals(2, params.length);
        
        IntParam p0 = (IntParam)params[0];
        IntParam p1 = (IntParam)params[1];
        
        assertEquals(0, p0.getInt());
        assertEquals(35623, p1.getInt());
    }
    
    @Test
    public void testParamsAndBinary() {
        IMBPacket packet = new IMBPacket();
    }
    
}
