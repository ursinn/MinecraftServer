package com.github.joshj1091.mcserver.protocol;

import com.github.joshj1091.mcserver.protocol.packets.HandshakePacket;
import com.github.joshj1091.mcserver.protocol.packets.PingRequestPacket;
import com.github.joshj1091.mcserver.protocol.packets.StatusRequestPacket;
import com.github.joshj1091.mcserver.util.ByteReader;

public class Protocol {

    /**
     * Get the packet type based on the state, direction and id
     * @param state the state
     * @param direction the direction of the packet
     * @param id the id of the packet
     * @return the type of packet
     */
    public static Packet getPacket(int state, Direction direction, ByteReader buffer, int id) {
        if (state == 0) {
            if (direction == Direction.SERVERBOUND) {
                if (id == 0x00) {
                    return new HandshakePacket(buffer, direction);
                }
            }
        } else if (state == 1) {
            if (direction == Direction.SERVERBOUND) {
                if (id == 0x00) {
                    return new StatusRequestPacket(buffer, direction);
                } else if (id == 0x01) {
                    return new PingRequestPacket(buffer, direction);
                }
            }
        }

        return null;
    }
}
