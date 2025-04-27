package com.mvc.mvc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;

@Component
public class Client1 implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Client1(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean validInput = false;

        while(true){
            System.out.println("Enter Person details <name,email,age> (q to quit):"); 
            input = scanner.nextLine();

            validInput = input.matches("[a-zA-Z]+,[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,},\\d+");

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Client1 quiting...");
                break;
            }
            else if(!validInput){
                System.out.println("Invalid input.");
                continue;
            }

            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(MvcApplication.topicExchangeName,
                    "foo.bar.baz",
                    input);
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        }
        scanner.close();
        System.out.println("Client1 successfully quit. Goodbye!");
    }
}