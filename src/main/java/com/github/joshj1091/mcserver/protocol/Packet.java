package com.github.joshj1091.mcserver.protocol;

public abstract class Packet {

    private byte[] buffer;
    private Direction direction;

    public Packet(byte[] data, Direction direction) {
        this.buffer = data;
        this.direction = direction;
    }

    public abstract int getId();

    public byte[] getData() {
        return buffer;
    }

    public Direction getDirection() {
        return direction;
    }

}
