package com.github.joshj1091.mcserver;

import com.github.joshj1091.mcserver.connection.UserConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MCServer {

    private static MCServer instance;

    private ServerSocket serverSocket;
    private boolean running = true;

    public MCServer() throws Exception {
        instance = this;
        log("MinecraftServer has started");

        this.serverSocket = new ServerSocket(25565);

        log("Accepting connections");

        new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        final Socket socket = serverSocket.accept();
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    new UserConnection(socket);
                                } catch (IOException ex) {
                                    if (ex instanceof EOFException) {
                                        log("Connection terminated");
                                    } else {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }.start();
                    } catch (IOException ex) {
                        return;
                    }
                }
            }
        }.start();
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
