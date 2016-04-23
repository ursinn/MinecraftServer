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
    /*private final String serverAddress;
    private final short serverPort;
    private final int nextState;*/

    public HandshakePacket(byte[] data, Direction direction) {
        super(data, direction);

        if (data.length == 0) {
            protocolVersion = 1;
            return;
        }

        ByteReader reader = new ByteReader(data);
        protocolVersion = DataInputUtil.readUnsignedInt(reader);
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    /*public String getServerAddress() {
        return serverAddress;
    }

    public short getServerPort() {
        return serverPort;
    }

    public int getNextState() {
        return nextState;
    }*/

    @Override
    public int getId() {
        return 0x00;
    }
}
