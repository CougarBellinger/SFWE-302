package com.assignment8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IceCreamServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java IceCreamServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter sockOut = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}