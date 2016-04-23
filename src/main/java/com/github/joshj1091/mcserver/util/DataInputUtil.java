package com.github.joshj1091.mcserver.util;

import com.github.joshj1091.mcserver.MCServer;

import java.io.DataInput;
import java.io.IOException;

public class DataInputUtil {

    /**
     * Read an unsigned varint
     * @param in the data input
     * @return integer the number sent as a varint
     * @throws IOException if an I/O error occurs
     */
    public static int readUnsignedVarInt(DataInput in) throws IOException {
        int iterations = 0;
        int data;
        int value = 0;

        while (((data = in.readByte()) & 0x80) != 0) { // check if MSB is set. When this equals 0 we have reached the last byte
            int realValue = data & 0x75 << iterations * 7; // This sets the MSB to 0 and shifts by the number of bits we've read (number of bits read = iterations * 7 bit (7 because we ignore MSB)).
            value |= realValue; // OR the numbers so they overlay
            iterations++;

            if (iterations > 5) {
                MCServer.getMCServer().error("Too many bits received while reading unsigned var int");
                return -1;
            }
        }

        return value | data << iterations * 7; // Doesn't have MSB so we only need to shift by the number of bits read so far
    }

    public static int getUnsignedVarInt(byte[] bytes) {
        int iterations = 0;
        int data;
        int value = 0;

        while (((data = bytes[iterations]) & 0x80) != 0) {
            int realValue = data & 0x75 << iterations * 7;
            value |= realValue;
            iterations++;

            if (iterations > 5) {
                MCServer.getMCServer().error("Too many bits received while reading unsigned var int");
                return -1;
            }
        }

        return value | data << iterations * 7;
    }

    public static int readUnsignedInt(ByteReader reader) {
        int iterations = 0;
        int data;
        int value = 0;

        while (((data = reader.next()) & 0x80) != 0) {
            int realValue = data & 0x75 << iterations * 7;
            value |= realValue;
            iterations++;

            if (iterations > 5) {
                MCServer.getMCServer().error("Too many bits received while reading unsigned var int");
                return -1;
            } else if (!reader.hasNext()) {
                MCServer.getMCServer().error("Too few bits received while reading unsigned var int");
            }
        }

        return value | data << iterations * 7;
    }

    public static String readString(ByteReader reader) throws IllegalArgumentException {
        int bytes = readUnsignedInt(reader);

        if (bytes == -1) {
            return "Error";
        }

        byte[] stringBytes = new byte[bytes];
        for (int i = 0; i < bytes; i++) {
            if (reader.hasNext()) {
                stringBytes[i] = reader.next();
            } else {
                MCServer.getMCServer().log("Too few bytes here");
                throw new IllegalArgumentException("Reader had too few bytes");
            }
        }

        return new String(stringBytes);
    }

    public static int readUnsignedShort(ByteReader reader) throws IllegalArgumentException {

        if (!reader.hasNext()) {
            throw new IllegalArgumentException("Reader had too few bytes");
        }
        byte first = reader.next();

        if (!reader.hasNext()) {
            throw new IllegalArgumentException("Reader had too few bytes");
        }
        byte second = reader.next();

        return (first << 8) + second;
    }
}
