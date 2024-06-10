package br.com.springboot.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.springboot.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {

        logger.info("Finding person");

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("John");
        person.setLastName("Doo");
        person.setAddress("123 Main Street");
        person.setGender("Male");

        return person;
    }
}
