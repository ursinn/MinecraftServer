package com.github.joshj1091.mcserver.protocol;

public abstract class OutgoingPacket implements Packet {

    public abstract byte[] encode();

    public abstract int getId();
}
