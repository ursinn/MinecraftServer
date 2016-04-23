package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataUtil;

/**
 * This class represents the handshake packet
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
 * Handshake Data Format
 *
 * | Field            | Data Type      |
 * -------------------------------------
 * | Protocol Version | VarInt         |
 * | Server Address   | String         |
 * | Server Port      | Unsigned Short |
 * | Next State       | VarInt         |
 *
 */
public class HandshakePacket extends Packet {

    private final int protocolVersion;
    private final String serverAddress;
    private final int serverPort;
    private final int nextState;

    public HandshakePacket(ByteReader reader, Direction direction) {
        super(reader.getData(), direction);

        protocolVersion = DataUtil.readUnsignedVarInt(reader);
        serverAddress = DataUtil.readString(reader);
        serverPort = DataUtil.readUnsignedShort(reader);
        nextState = DataUtil.readUnsignedVarInt(reader);
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getNextState() {
        return nextState;
    }

    @Override
    public int getId() {
        return 0x00;
    }
}
