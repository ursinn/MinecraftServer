package com.github.joshj1091.mcserver.protocol.packets.outgoing;

import com.github.joshj1091.mcserver.protocol.OutgoingPacket;
import com.github.joshj1091.mcserver.util.DataUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class represents the pong response packet that is clientbound.
 * <p>
 * Pong Response Data Format
 * <p>
 * | Field            | Data Type      |
 * -------------------------------------
 * | Long             | Long           |
 */
public class PongResponsePacket extends OutgoingPacket {

    private byte[] longBytes;

    public PongResponsePacket(byte[] longBytes) {
        this.longBytes = longBytes;
    }

    /**
     * This produces a byte array that starts with the packet id and ends with the
     * long (in bytes) from the client
     *
     * @return byte array
     */
    @Override
    public byte[] encode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(DataUtil.intToUnsignedVarInt(getId()));
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
