package br.com.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.springboot.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating person");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating person");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting person");
    }

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

    public List<Person> findAll() {
        logger.info("Finding all people...");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address in Brazil " + i);
        person.setGender("Male");

        return person;
    }
}
