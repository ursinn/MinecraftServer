package com.github.joshj1091.mcserver.connection;

import com.github.joshj1091.mcserver.MCServer;
import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.protocol.Protocol;
import com.github.joshj1091.mcserver.protocol.packets.incoming.HandshakePacket;
import com.github.joshj1091.mcserver.protocol.packets.incoming.LoginStartPacket;
import com.github.joshj1091.mcserver.protocol.packets.incoming.PingRequestPacket;
import com.github.joshj1091.mcserver.protocol.packets.outgoing.PongResponsePacket;
import com.github.joshj1091.mcserver.protocol.packets.outgoing.StatusResponsePacket;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class UserConnection {

    private final MCServer server = MCServer.getMCServer();
    private Socket socket;
    private boolean acceptData = true;

    private int state;

    /**
     *
     * Standard Packet Format
     *
     * | Field            | Data Type      |
     * -------------------------------------
     * | Packet Size      | VarInt         |
     * | Packet ID        | VarInt         |
     * | Data             | Byte Array     |
     *
     * @param socket the connection socket
     * @throws IOException if there are any I/O errors
     */
    public UserConnection(final Socket socket) throws IOException {
        this.socket = socket;
        this.state = 0;

        server.log("Accepted connection from " + socket.getInetAddress().toString());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        while (acceptData) {
            int size = DataUtil.readUnsignedVarInt(inputStream);
            byte[] buffer = new byte[size];
            inputStream.readFully(buffer);
            ByteReader reader = new ByteReader(buffer);
            int id = DataUtil.readUnsignedVarInt(reader);

            Packet packet = Protocol.getPacket(state, Direction.SERVERBOUND, reader, id);
            handlePacket(packet);
        }
    }

    private void handlePacket(Packet packet) {
        if (state == 0) {
            if (packet.getId() == 0x00) { // handshake packet
                HandshakePacket handshakePacket = (HandshakePacket) packet;
                this.state = handshakePacket.getNextState();
            }
        } else if (state == 1) {
            if (packet.getId() == 0x00) { // status request
                server.log("Got status request");

                StatusResponsePacket response = new StatusResponsePacket("1.9.0", 107, 50, 5, "Hello from Josh's server");
                byte[] data = response.encode();

                write(DataUtil.intToUnsignedVarInt(data.length)); // send the length of the byte array first so the client knows how much to read
                write(data);
            } else if (packet.getId() == 0x01) {
                server.log("Got ping request");

                PingRequestPacket pingRequestPacket = (PingRequestPacket) packet;
                PongResponsePacket response = new PongResponsePacket(pingRequestPacket.getLongBytes());

                byte[] data = response.encode();
                write(DataUtil.intToUnsignedVarInt(data.length));
                write(data);
            }
        } else if (state == 2) {
            if (packet.getId() == 0x00) {
                server.log("Login start packet");
                LoginStartPacket loginStartPacket = (LoginStartPacket) packet;
                server.log("Found name: " + loginStartPacket.getName());

                try {
                    socket.close();
                } catch (IOException ex) {

                }
            }
        }
    }

    private void write(byte[] data) {
        try {
            socket.getOutputStream().write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
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
