package br.com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
