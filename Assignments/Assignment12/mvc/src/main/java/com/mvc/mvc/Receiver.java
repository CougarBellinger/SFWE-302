package com.mvc.mvc;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private Validator validator;
    
    @Autowired
    private PersonService personService;

    public void receiveMessage(String personText) {
        String[] parts = personText.split(",");
        Person person = new Person(parts[0], parts[1], Integer.parseInt(parts[2]));

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Person> violation : violations) {
                sb.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException("Invalid Person: " + sb.toString());
        }

        personService.addPerson(person);

        System.out.println("Received <" + personText + ">");
        System.out.println("Person created: " + person.getName() + ", " + person.getEmail() + ", " + person.getAge());

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

