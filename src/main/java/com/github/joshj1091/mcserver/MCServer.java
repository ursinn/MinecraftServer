package com.github.joshj1091.mcserver;

import com.github.joshj1091.mcserver.protocol.Direction;
import com.github.joshj1091.mcserver.protocol.Packet;
import com.github.joshj1091.mcserver.util.DataInputUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCServer {

    private static MCServer instance;

    private Thread connectionThread;
    private ServerSocket serverSocket;

    public MCServer() throws Exception {
        instance = this;
        log("MinecraftServer has started");

        this.serverSocket = new ServerSocket(25565);

        connectionThread = new Thread() {
            @Override
            public void run() {
                log("Now accepting connections");
                try {
                    Socket socket = serverSocket.accept(); // wait for new connection
                    log("Accepted connection");

                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                    int size = DataInputUtil.readUnsignedVarInt(inputStream);

                    /*if (packetID == 0x00) {
                        log("Minecraft Version: " + DataInputUtil.readUnsignedVarInt(inputStream));
                        int stringBytes = DataInputUtil.readUnsignedVarInt(inputStream);
                        log("String bytes: " + stringBytes);

                        byte[] bytes = new byte[stringBytes];
                        for (int i = 0; i < stringBytes; i++) {
                            bytes[i] = inputStream.readByte();
                        }

                        log("Hostname: " + new String(bytes));
                        log("Port: " + inputStream.readUnsignedShort());
                        log("Next State: " + DataInputUtil.readUnsignedVarInt(inputStream));


                        log("Handshake packet");
                    }*/

                    byte[] buffer = new byte[size];
                    int read = inputStream.read(buffer);
                    if (read < size) {
                        error("Read too few bytes");
                        return;
                    }
                    int id = DataInputUtil.getUnsignedVarInt(buffer);

                    Packet packet = new Packet(id, Arrays.copyOfRange(buffer, id + 1, buffer.length), Direction.SERVERBOUND);
                } catch (IOException ex) {
                    if (ex instanceof EOFException) {
                        error("Reached end of stream");
                    } else {
                        error("IOException when accepting Socket");
                        ex.printStackTrace();
                    }
                }
            }
        };

        connectionThread.start();
    }



    public static MCServer getMCServer() {
        return instance;
    }

    public void log(String message) {
        System.out.println("INFO: " + message);
    }

    public void error(String message) {
        System.out.println("ERROR: " + message);
    }
}
