package com.github.joshj1091.mcserver.protocol;

public abstract class IncomingPacket implements Packet {

    private final byte[] bytes;

    public IncomingPacket(byte[] bytes) {
        this.bytes = bytes;
    }

    public abstract int getId();

    public byte[] getData() {
        return bytes;
    }
}
