package com.api_register.register.of.people.and.address.controller;


import com.api_register.register.of.people.and.address.dto.PersonSaveDto;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public ResponseEntity<Person> save(@RequestBody PersonSaveDto personSaveDto) {

        var response = personService.Save(personSaveDto);

        return ResponseEntity.created(URI.create("./person/" + response.toString())).build();

    }

}
