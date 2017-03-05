package com.lagopusempire.moonblast.params;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParamTypeTest {
    
    public ParamTypeTest() {
    }

    @Test
    public void testCorrectIndexes() {
        ParamType[] values = ParamType.values();
        for(int ii = 0; ii < values.length; ii++) {
            ParamType type = values[ii];
            ParamType typeReceived = ParamType.get(ii);
            assertEquals(type, typeReceived);
        }
    }
    
    @Test
    public void testRandomIndex() {
        //I know that int is 2
        assertEquals(ParamType.INT, ParamType.get(2));
    }
}
