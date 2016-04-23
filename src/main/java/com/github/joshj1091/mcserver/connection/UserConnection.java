package com.github.joshj1091.mcserver.connection;

import com.github.joshj1091.mcserver.MCServer;
import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.protocol.Protocol;
import com.github.joshj1091.mcserver.protocol.packets.HandshakePacket;
import com.github.joshj1091.mcserver.util.DataInputUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class UserConnection {

    private final MCServer server = MCServer.getMCServer();
    private Socket socket;
    private boolean acceptData = true;

    private int state;

    public UserConnection(final Socket socket) throws IOException {
        this.socket = socket;
        this.state = 0;

        server.log("Accepted connection from " + socket.getInetAddress().toString());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        while (acceptData) {
            int size = DataInputUtil.readUnsignedVarInt(inputStream);
            byte[] buffer = new byte[size];
            inputStream.readFully(buffer);
            int id = DataInputUtil.getUnsignedVarInt(buffer);


            Packet packet = Protocol.getPacket(state, Direction.SERVERBOUND, Arrays.copyOfRange(buffer, id + 1, buffer.length), id);
            handlePacket(packet);
        }
    }

    private void handlePacket(Packet packet) {
        if (state == 0) {
            if (packet.getId() == 0x00) {
                server.log("Found handshake packet");
                HandshakePacket handshakePacket = (HandshakePacket) packet;
                server.log("Protocol Version: " + handshakePacket.getProtocolVersion());
                server.log("Server Address: " + handshakePacket.getServerAddress());
                server.log("Server port: " + handshakePacket.getServerPort());
                server.log("Next state: " + handshakePacket.getNextState());

                this.state = handshakePacket.getNextState();
            }
        }
    }

    /**
     * Set the protocol state
     * @param state
     */
    private void setState(int state) {
        this.state = state;
    }

    private int getState() {
        return state;
    }

    public void close() throws IOException {
        acceptData = false;
        socket.close();
    }
}
