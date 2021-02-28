package com.github.joshj1091.mcserver.protocol.packets.incoming;

import com.github.joshj1091.mcserver.protocol.IncomingPacket;
import com.github.joshj1091.mcserver.util.ByteReader;
import lombok.Getter;

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
 * Ping Request Data Format
 *
 * | Field            | Data Type      |
 * -------------------------------------
 * | Long             | Long           |
 */
public class PingRequestPacket extends IncomingPacket {

    /**
     * The bytes that represent the long sent from the Minecraft client
     *
     * the bytes in an array
     */
    @Getter private final byte[] longBytes;

    public PingRequestPacket(ByteReader reader) {
        super(reader.getBuffer());

        this.longBytes = Arrays.copyOfRange(reader.getBuffer(), 1, getBytes().length); // this should always be 8 bytes
    }

    public int getId() {
        return 0x01;
    }
}
