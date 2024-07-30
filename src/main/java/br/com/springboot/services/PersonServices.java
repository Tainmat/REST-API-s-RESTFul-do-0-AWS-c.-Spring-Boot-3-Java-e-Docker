package br.com.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.springboot.controllers.PersonController;
import br.com.springboot.data.vo.v1.PersonVO;

import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.mapper.DozerMapper;
import br.com.springboot.mapper.custom.PersonMapper;
import br.com.springboot.model.Person;
import br.com.springboot.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    public PersonVO create(PersonVO person) {
        logger.info("Creating person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var saveEntity = personRepository.save(entity);
        var entityVO = DozerMapper.parseObject(saveEntity, PersonVO.class);

        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());

        return entityVO;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating person");

        var entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = personRepository.save(entity);

        var entityVO = DozerMapper.parseObject(vo, PersonVO.class);

        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());

        return entityVO;
    }

    public void delete(Long id) {
        logger.info("Deleting person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        ;

        var entityVO = DozerMapper.parseObject(entity, PersonVO.class);
        entityVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return entityVO;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");

        var entityVO = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

        entityVO.stream().forEach(
                item -> item.add(linkTo(methodOn(PersonController.class).findById(item.getKey())).withSelfRel()));

        return entityVO;
    }

}
