package com.github.joshj1091.mcserver.protocol.packets.outgoing;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.DataUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class LoginDisconnectPacket extends Packet {

    private String disconnectMessage;

    public LoginDisconnectPacket(String disconnectMessage) {
        super(Direction.CLIENTBOUND);

        this.disconnectMessage = disconnectMessage;
    }

    public byte[] encode() {
        JsonObject payload = new JsonObject();

        payload.addProperty("text", disconnectMessage);

        byte[] packetId = DataUtil.intToUnsignedVarInt(getId());
        byte[] messageBytes = new Gson().toJson(payload).getBytes();
        byte[] messageLength = DataUtil.intToUnsignedVarInt(messageBytes.length);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(packetId);
            outputStream.write(messageLength);
            outputStream.write(messageBytes);
        } catch (IOException ex) {

        }

        return outputStream.toByteArray();
    }

    public int getId() {
        return 0x00;
    }
}
