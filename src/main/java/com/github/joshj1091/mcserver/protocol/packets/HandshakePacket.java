package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataInputUtil;

/**
 * This class represents the handshake packet
 */
public class HandshakePacket extends Packet {

    private final int protocolVersion;
    private final String serverAddress;
    private final int serverPort;
    private final int nextState;

    public HandshakePacket(byte[] data, Direction direction) {
        super(data, direction);

        ByteReader reader = new ByteReader(data);
        protocolVersion = DataInputUtil.readUnsignedInt(reader);
        serverAddress = DataInputUtil.readString(reader);
        serverPort = DataInputUtil.readUnsignedShort(reader);
        nextState = DataInputUtil.readUnsignedInt(reader);
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
