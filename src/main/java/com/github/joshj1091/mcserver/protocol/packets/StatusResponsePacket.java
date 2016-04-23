package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.DataInputUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StatusResponsePacket extends Packet {

    private String versionName;
    private int protocolVersion;
    private int maxPlayers;
    private int onlinePlayers;
    private String descriptionText;

    public StatusResponsePacket(String versionName, int protocolVersion, int maxPlayers, int onlinePlayers, String descriptionText) {
        super(Direction.CLIENTBOUND);

        this.versionName = versionName;
        this.protocolVersion = protocolVersion;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
        this.descriptionText = descriptionText;
    }

    public byte[] encode() {

        JsonObject payload = new JsonObject();

        JsonObject version = new JsonObject();
        version.addProperty("name", versionName);
        version.addProperty("protocol", protocolVersion);

        payload.add("version", version);

        JsonObject players = new JsonObject();
        players.addProperty("max", maxPlayers);
        players.addProperty("online", onlinePlayers);

        payload.add("players", players);

        JsonObject description = new JsonObject();
        description.addProperty("text", descriptionText);

        payload.add("description", description);

        Gson gson = new Gson();
        byte[] jsonBytes = gson.toJson(payload).getBytes();

        byte[] jsonLength = DataInputUtil.intToUnsignedVarInt(jsonBytes.length);

        byte[] protocolVersion = DataInputUtil.intToUnsignedVarInt(getId());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(protocolVersion);
            outputStream.write(jsonLength);
            outputStream.write(jsonBytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    public int getId() {
        return 0x00;
    }
}
