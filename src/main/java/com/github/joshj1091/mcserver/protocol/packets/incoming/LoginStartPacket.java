package com.github.joshj1091.mcserver.protocol.packets.incoming;

import com.github.joshj1091.mcserver.protocol.IncomingPacket;
import com.github.joshj1091.mcserver.util.ByteReader;
import com.github.joshj1091.mcserver.util.DataUtil;
import lombok.Getter;

public class LoginStartPacket extends IncomingPacket {

    @Getter private final String name;

    public LoginStartPacket(ByteReader reader) {
        super(reader.getBuffer());

        this.name = DataUtil.readString(reader);
    }

    @Override
    public int getId() {
        return 0x00;
    }
}
