package br.com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.data.vo.v2.PersonVOV2;
import br.com.springboot.services.PersonServices;
import br.com.springboot.util.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML }, consumes = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PostMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML })
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

}
