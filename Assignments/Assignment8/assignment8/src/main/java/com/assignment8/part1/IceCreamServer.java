package com.assignment8.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class IceCreamServer {
    private static final Map<String, Integer> ICE_CREAM_PRICES = new HashMap<>();

    static {
        ICE_CREAM_PRICES.put("Vanilla", 2);
        ICE_CREAM_PRICES.put("Chocolate", 2);
        ICE_CREAM_PRICES.put("Lemon", 1);
    }

    private enum ClientState {
        GREETING,
        AWAITING_ORDER,
        AWAITING_PAYMENT,
        TRANSACTION_COMPLETE
    }

    private static void handleClient(Socket clientSocket) {
        ClientState state = ClientState.GREETING;
        String selectedIceCream = null;
        int cost = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Client connected: " + clientSocket);

            while (state != ClientState.TRANSACTION_COMPLETE) {
                switch (state) {
                    case GREETING:
                        out.println("Hey, what Ice Cream can I offer? (Options: Vanilla, Chocolate, Lemon)");
                        state = ClientState.AWAITING_ORDER;
                        break;

                    case AWAITING_ORDER:
                        String orderInput = in.readLine();
                        if (orderInput == null) {
                            return; // Client disconnected
                        }
                        Integer price = ICE_CREAM_PRICES.get(orderInput);
                        if (price != null) {
                            selectedIceCream = orderInput;
                            cost = price;
                            out.println("$" + cost + ", please");
                            state = ClientState.AWAITING_PAYMENT;
                        } else {
                            out.println("Invalid choice. Please choose from Vanilla, Chocolate, or Lemon");
                        }
                        break;

                    case AWAITING_PAYMENT:
                        String paymentInput = in.readLine();
                        if (paymentInput == null) {
                            return; // Client disconnected
                        }
                        try {
                            int clientPayment = Integer.parseInt(paymentInput.substring(1));
                            if (clientPayment < cost) {
                                out.println("Not enough. $" + (cost - clientPayment) + " please");
                            } else if (clientPayment == cost) {
                                out.println("Here you are! Thank you");
                                state = ClientState.TRANSACTION_COMPLETE;
                            } else {
                                out.println("Here $" + (clientPayment - cost) + " back");
                                state = ClientState.TRANSACTION_COMPLETE;
                            }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                            out.println("Invalid payment format. Please enter the amount (e.g., $2)");
                        }
                        break;

                    case TRANSACTION_COMPLETE:
                        // Exit the loop
                        break;
                }
            }

            System.out.println("Client disconnected: " + clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final int PORT = 1234;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client connection in a new thread
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}