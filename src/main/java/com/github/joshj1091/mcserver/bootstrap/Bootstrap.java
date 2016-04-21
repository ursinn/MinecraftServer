package com.github.joshj1091.mcserver.bootstrap;

import com.github.joshj1091.mcserver.MCServer;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        MCServer server = new MCServer();

        boolean run = true;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext() && run) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                run = false;
                server.log("Stopping");
                server.stop();
                server.log("Closing now");
                System.exit(0);
            }

        }
    }
}
