package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.ByteReader;

import java.util.Arrays;

/**
 * This class represents the ping request packet from the client.
 *
 * Standard Packet Format
 *
 * | Field            | Data Type      |
 * -------------------------------------
 * | Packet Size      | VarInt         |
 * | Packet ID        | VarInt         |
 * | Data             | Byte Array     |
 *
 *
 * Ping Request Data Format
 *
 * | Field            | Data Type      |
 * -------------------------------------
 * | Long             | Long           |
 *
 */
public class PingRequestPacket extends Packet {

    private byte[] longBytes;

    public PingRequestPacket(ByteReader reader, Direction direction) {
        super(reader.getData(), direction);

        this.longBytes = Arrays.copyOfRange(reader.getData(), 1, getData().length); // this should always be 8 bytes
    }

    /**
     * The bytes that represent the long sent from the Minecraft client
     * @return the bytes in an array
     */
    public byte[] getLongBytes() {
        return longBytes;
    }

    public int getId() {
        return 0x01;
    }
}
