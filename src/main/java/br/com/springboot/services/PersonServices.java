package br.com.springboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.mapper.DozerMapper;
import br.com.springboot.model.Person;
import br.com.springboot.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonVO create(PersonVO person) {
        logger.info("Creating person");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = personRepository.save(entity);

        var entityVO = DozerMapper.parseObject(vo, PersonVO.class);

        return entityVO;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating person");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = personRepository.save(entity);

        var entityVO = DozerMapper.parseObject(vo, PersonVO.class);

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

        return DozerMapper.parseObject(entity, PersonVO.class);

    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

}
