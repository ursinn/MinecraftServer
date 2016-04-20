package com.github.joshj1091.mcserver.bootstrap;

import com.github.joshj1091.mcserver.MCServer;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) {
        MCServer server = new MCServer();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.next();

            server.log(input);
        }
    }
}
