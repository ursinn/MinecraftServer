package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.ByteReader;

public class PingRequestPacket extends Packet {

    public PingRequestPacket(ByteReader reader, Direction direction) {
        super(reader.getData(), direction);
    }

    public int getId() {
        return 0x01;
    }
}
