package com.github.joshj1091.mcserver;

public class MCServer {

    public MCServer() {
        log("MinecraftServer has started");
    }

    public void log(String message) {
        System.out.println("INFO: " + message);
    }

    public void error(String message) {
        System.out.println("ERROR: " + message);
    }
}
