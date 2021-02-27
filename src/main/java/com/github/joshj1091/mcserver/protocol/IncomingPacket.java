package com.github.joshj1091.mcserver.protocol;

public abstract class IncomingPacket implements Packet {

    private final byte[] bytes;

    protected IncomingPacket(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getData() {
        return bytes;
    }
}
