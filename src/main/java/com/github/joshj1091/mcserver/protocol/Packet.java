package com.github.joshj1091.mcserver.protocol;

import com.github.joshj1091.mcserver.MCServer;

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

    public int getUnsignedVarInt() {
        int iterations = 0;
        int data;
        int value = 0;

        while (((data = buffer[iterations]) & 0x80) != 0) {
            int realValue = data & 0x75 << iterations * 7;
            value |= realValue;
            iterations++;

            if (iterations > 5) {
                MCServer.getMCServer().error("Too many bits received while reading unsigned var int");
                return -1;
            }
        }



        return value | data << iterations * 7;
    }
}
