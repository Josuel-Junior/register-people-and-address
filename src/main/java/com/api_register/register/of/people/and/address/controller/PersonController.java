package com.api_register.register.of.people.and.address.controller;


import com.api_register.register.of.people.and.address.dto.PersonSaveDto;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.service.PersonService;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody PersonSaveDto personSaveDto) {

        var response = personService.Save(personSaveDto);

        return ResponseEntity.created(URI.create("./person/" + response.toString())).build();

    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerons(){

        var responseList = personService.getAll();

        return ResponseEntity.ok(responseList);
    }

}
