package com.github.joshj1091.mcserver.protocol.packets.incoming;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.ByteReader;

/**
 * This class represents the status request packet. This packet doesn't have a payload.
 */
public class StatusRequestPacket extends Packet {

    public StatusRequestPacket(ByteReader reader, Direction direction) {
        super(reader.getData(), direction);
    }

    public int getId() {
        return 0x00;
    }
}
