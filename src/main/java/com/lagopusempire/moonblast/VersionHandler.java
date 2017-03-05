package com.lagopusempire.moonblast;

public class VersionHandler {
    private static final int VERSION = 1;
    
    public boolean isVersionValid(int version) {
        return version == VERSION;
    }
    
    public int getVersion() {
        return VERSION;
    }
}
