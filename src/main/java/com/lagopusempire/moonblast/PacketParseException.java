package com.lagopusempire.moonblast;

public class PacketParseException extends RuntimeException {
    public PacketParseException(String message) {
        super("Failed to parse packet: " + message);
    }
}
