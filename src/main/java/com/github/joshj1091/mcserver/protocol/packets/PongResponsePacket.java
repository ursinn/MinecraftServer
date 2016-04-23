package com.github.joshj1091.mcserver.protocol.packets;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.DataInputUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class represents the pong response packet that is clientbound.
 *
 * Pong Response Data Format
 *
 * | Field            | Data Type      |
 * -------------------------------------
 * | Long             | Long           |
 */
public class PongResponsePacket extends Packet {

    private byte[] longBytes;

    public PongResponsePacket(byte[] longBytes) {
        super(Direction.CLIENTBOUND);

        this.longBytes = longBytes;
    }

    /**
     * This produces a byte array that starts with the packet id and ends with the
     * long (in bytes) from the client
     * @return byte array
     */
    public byte[] encode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(DataInputUtil.intToUnsignedVarInt(getId()));
            outputStream.write(longBytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    @Override
    public int getId() {
        return 0x01;
    }
}
