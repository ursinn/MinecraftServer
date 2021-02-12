package com.github.joshj1091.mcserver.protocol.packets.incoming;

import com.github.joshj1091.mcserver.protocol.IncomingPacket;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataUtil;

public class LoginStartPacket extends IncomingPacket {

    private final String name;

    public LoginStartPacket(ByteReader reader) {
        super(reader.getData());

        this.name = DataUtil.readString(reader);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return 0x00;
    }
}
