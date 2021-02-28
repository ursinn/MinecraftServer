package com.github.joshj1091.mcserver.protocol.packets.incoming;

import com.github.joshj1091.mcserver.protocol.IncomingPacket;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataUtil;
import lombok.Getter;

/**
 * This class represents the handshake packet
 * <p>
 * Standard Packet Format
 * <p>
 * | Field            | Data Type      |
 * -------------------------------------
 * | Packet Size      | VarInt         |
 * | Packet ID        | VarInt         |
 * | Data             | Byte Array     |
 * <p>
 * Handshake Data Format
 * <p>
 * | Field            | Data Type      |
 * -------------------------------------
 * | Protocol Version | VarInt         |
 * | Server Address   | String         |
 * | Server Port      | Unsigned Short |
 * | Next State       | VarInt         |
 */
public class HandshakePacket extends IncomingPacket {

    @Getter
    private final int protocolVersion;
    @Getter
    private final String serverAddress;
    @Getter
    private final int serverPort;
    @Getter
    private final int nextState;

    public HandshakePacket(ByteReader reader) {
        super(reader.getBuffer());

        protocolVersion = DataUtil.readUnsignedVarInt(reader);
        serverAddress = DataUtil.readString(reader);
        serverPort = DataUtil.readUnsignedShort(reader);
        nextState = DataUtil.readUnsignedVarInt(reader);
    }

    @Override
    public int getId() {
        return 0x00;
    }
}
