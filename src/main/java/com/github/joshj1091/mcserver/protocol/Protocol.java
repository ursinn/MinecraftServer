package com.github.joshj1091.mcserver.protocol;

import com.github.joshj1091.mcserver.protocol.packets.HandshakePacket;

public class Protocol {

    /**
     * Get the packet type based on the state, direction and id
     * @param state the state
     * @param direction the direction of the packet
     * @param id the id of the packet
     * @return the type of packet
     */
    public static Packet getPacket(int state, Direction direction, byte[] buffer, int id) {
        if (state == 0) {
            if (direction == Direction.SERVERBOUND) {
                if (id == 0x00) {
                    return new HandshakePacket(buffer, direction);
                }
            }
        } else if (state == 1) {
        }

        return null;
    }
}
