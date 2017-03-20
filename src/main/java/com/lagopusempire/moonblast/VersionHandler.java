package com.lagopusempire.moonblast;

/**
 * This class handles what could be complex logic for determining if a packet
 * version is compatible or not. So far there's only one version of the 
 * MoonBlast protocol out there, so this class is mind numbingly simple.
 * @author Matt
 */
public class VersionHandler {
    /**
     * The newest version of the protocol that this implementation knows about.
     */
    private static final byte VERSION = 1;
    
    /**
     * Checks if the version is valid or not.
     * @param version The version to check.
     * @return True if the version is valid, false if not.
     */
    public boolean isVersionValid(int version) {
        return version == VERSION;
    }
    
    /**
     * Gets the latest version that this implementation knows about. Used
     * for creating packets.
     * @return The current version (from this library's perspective).
     */
    public byte getVersion() {
        return VERSION;
    }
}
