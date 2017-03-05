package com.lagopusempire.moonblast;

import com.lagopusempire.moonblast.params.IMBParam;

public class IMBPacket {
    static final byte PACKET_START = '(';
    static final byte PACKET_END = ')';
    
    static final int VERSION = 1;
    
    
    
    public IMBParam[] getParams() {
        return null;
    }
    
    public int getParamsLength() {
        return 0;
    }
    
    byte[] getData() {
        return null;
    }
}
