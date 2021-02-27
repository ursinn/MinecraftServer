package com.github.joshj1091.mcserver;

import com.github.joshj1091.mcserver.connection.UserConnection;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MCServer {

    private static MCServer instance;

    private final ServerSocket serverSocket;
    private boolean running = true;

    public MCServer() throws IOException {
        instance = this;
        log("MinecraftServer has started");

        this.serverSocket = new ServerSocket(25565);

        log("Accepting connections");

        new Thread(() -> {
            while (running) {
                try {
                    final Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            new UserConnection(socket);
                        } catch (EOFException ex) {
                            log("Connection terminated");
                        } catch (SocketException ex) {
                            log("Socket closed");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                } catch (IOException ex) {
                    return;
                }
            }
        }).start();
    }

    public static MCServer getMCServer() {
        return instance;
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void log(String message) {
        Logger.getGlobal().log(Level.INFO, message);
    }

    public void error(String message) {
        Logger.getGlobal().log(Level.SEVERE, message);
    }
}
