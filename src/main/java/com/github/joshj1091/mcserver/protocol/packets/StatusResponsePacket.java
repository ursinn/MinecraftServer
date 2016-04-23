package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.JSONObject;

public class StatusResponsePacket extends Packet {

    private String versionName;
    private int protocolVersion;
    private int maxPlayers;
    private int onlinePlayers;
    private String description;

    public StatusResponsePacket(String versionName, int protocolVersion, int maxPlayers, int onlinePlayers, String description) {
        super(Direction.CLIENTBOUND);

        this.versionName = versionName;
        this.protocolVersion = protocolVersion;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
        this.description = description;
    }

    public byte[] encode() {

        JSONWriter jsonWriter = new JSONWriter();

        return new byte[1024];
    }

    public int getId() {
        return 0x00;
    }
}
