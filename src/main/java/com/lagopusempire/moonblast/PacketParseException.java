package com.lagopusempire.moonblast;

/**
 * Thrown if something goes wrong while the packet is being parsed.
 * @author Matt
 */
public class PacketParseException extends RuntimeException {
    /**
     * Constructor.
     * @param message Details about what went wrong.
     */
    public PacketParseException(String message) {
        super("Failed to parse packet: " + message);
    }
}
