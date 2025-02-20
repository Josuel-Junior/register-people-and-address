package com.api_register.register.of.people.and.address.service;

import com.api_register.register.of.people.and.address.dto.PersonDto;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.exception.PersonNotFoundException;
import com.api_register.register.of.people.and.address.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {


    final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public UUID Save(PersonDto personDto) {

        var saveEnity = new Person(null, personDto.name(), personDto.birthdate(), null);

        var response = personRepository.save(saveEnity);

        return response.getId();
    }

    public List<Person> getAll() {

        return personRepository.findAll();
    }

    public void delete(String personId) {

        var id = UUID.fromString(personId);

        if (!personRepository.existsById(id)) {
            throw new PersonNotFoundException("Pessoa com ID " + id + " não encontrada.");
        }

        personRepository.deleteById(id);

    }

    public Optional<Person> getById(String personId) {

        var id = UUID.fromString(personId);

        return personRepository.findById(id);
    }

    public Person updatePerson(String personId, PersonDto personDto) {

        var id = UUID.fromString(personId);

        var personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException("Pessoa com ID " + id + " não encontrada.");
        }

        var person = personEntity.get();

        if (personDto.name() != null) {
            person.setName(personDto.name());
        }
        if (personDto.birthdate() != null) {
            person.setBirthdate(personDto.birthdate());
        }

        return personRepository.save(person);

    }


}
