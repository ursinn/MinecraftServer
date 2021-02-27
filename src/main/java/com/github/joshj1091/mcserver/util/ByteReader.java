package com.github.joshj1091.mcserver.util;

import lombok.Getter;

public class ByteReader {

    @Getter private final byte[] buffer;
    @Getter private int currentPos = 0;

    public ByteReader(byte[] data) {
        this.buffer = data;
    }

    public boolean hasNext() {
        return currentPos <= buffer.length;
    }

    public byte next() {
        return buffer[currentPos++];
    }
}
