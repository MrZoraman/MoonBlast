package com.lagopusempire.moonblast;

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
    
}
