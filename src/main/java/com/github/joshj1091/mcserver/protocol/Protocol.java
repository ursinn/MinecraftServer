package com.github.joshj1091.mcserver.protocol;

public class Protocol {

    /**
     * Get the packet type based on the state, direction and id
     * @param state the state
     * @param direction the direction of the packet
     * @param id the id of the packet
     * @return the type of packet
     */
    public static Packet.Type getPacketType(int state, Direction direction, int id) {
        if (state == 0) {
            if (direction == Direction.SERVERBOUND) {
                if (id == 0x00) {
                    return Packet.Type.HANDSHAKE;
                }
            }
        } else if (state == 1) {
            if (direction == Direction.SERVERBOUND) {
                if (id == 0x00) {
                    return Packet.Type.STATUS_REQUEST;
                } else if (id == 0x01) {
                    return Packet.Type.STATUS_PONG;
                }
            }
        }

        return null;
    }
}
