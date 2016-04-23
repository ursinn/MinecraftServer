package com.github.joshj1091.mcserver.util;

public class ByteReader {

    private byte[] buffer;
    private int currentPos = 0;

    public ByteReader(byte[] data) {
        this.buffer = data;
    }

    public boolean hasNext() {
        return currentPos <= buffer.length;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public byte next() {
        return buffer[currentPos++];
    }

    public byte[] getData() {
        return buffer;
    }
}
