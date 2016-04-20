package com.github.joshj1091.mcserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MCServer {

    private Thread connectionThread;
    private ServerSocket serverSocket;

    public MCServer() throws Exception {
        log("MinecraftServer has started");

        this.serverSocket = new ServerSocket(25565);

        connectionThread = new Thread() {
            @Override
            public void run() {
                log("Now accepting connections");
                try {
                    Socket socket = serverSocket.accept(); // wait for new connection

                } catch (IOException ex) {
                    error("IOException when accepting Socket");
                }
            }
        };

        connectionThread.start();
    }

    public void log(String message) {
        System.out.println("INFO: " + message);
    }

    public void error(String message) {
        System.out.println("ERROR: " + message);
    }
}
