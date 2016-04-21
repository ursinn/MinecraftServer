package com.github.joshj1091.mcserver.protocol;

public class Packet {

    private int id;
    private byte[] data;
    private Direction direction;

    public Packet(int id, byte[] data, Direction direction) {
        this.id = id;
        this.data = data;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public Direction getDirection() {
        return direction;
    }

}
