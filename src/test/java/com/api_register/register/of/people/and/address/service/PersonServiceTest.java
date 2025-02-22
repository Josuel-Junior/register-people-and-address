package com.api_register.register.of.people.and.address.service;

import com.api_register.register.of.people.and.address.dto.PersonDto;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.exception.PersonNotFoundException;
import com.api_register.register.of.people.and.address.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Captor
    private ArgumentCaptor<Person> personArgumentCaptor;


    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;


    @Nested
    class createdPerson {
        @Test
        @DisplayName("Should saved a person with success")
        void shouldSavePerson() {

            var person = new Person(
                    UUID.randomUUID(),
                    "name person",
                    "birthdate date",
                    null
            );

            doReturn(person).when(personRepository).save(personArgumentCaptor.capture());

            var input = new PersonDto("name person", "birthdate date");

            var output = personService.createdPerson(input);

            assertNotNull(output);

            var personCaptured = personArgumentCaptor.getValue();

            assertEquals(input.name(), personCaptured.getName());
            assertEquals(input.birthdate(), personCaptured.getBirthdate());

        }

        @Test
        @DisplayName("Should display an exception")
        void shouldThrowExceptionError() {

            doThrow(new RuntimeException()).when(personRepository).save(any());
            var input = new PersonDto("name person", "birthdate date");

            assertThrows(RuntimeException.class, () -> personService.createdPerson(input));


        }
    }

    @Nested
    class getPersonById {
        @Test
        @DisplayName("Should get person by id with success when optional is present")
        void shouldGetPersonByIdWithSuccessWhenOptionalIsPresent() {

            var person = new Person(
                    UUID.randomUUID(),
                    "name person",
                    "birthdate date",
                    null
            );
            doReturn(Optional.of(person)).when(personRepository).findById(uuidArgumentCaptor.capture());


            var output = personService.getById(person.getId().toString());

            assertTrue(output.isPresent());

            assertEquals(person.getId(), uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should get person by id with success when optional is empty")
        void shouldGetPersonByIdWithSuccessWhenOptionalIsEmpty() {

            var uuid = UUID.randomUUID();
            doReturn(Optional.empty()).when(personRepository).findById(uuidArgumentCaptor.capture());

            var output = personService.getById(uuid.toString());

            assertTrue(output.isEmpty());

            assertEquals(uuid, uuidArgumentCaptor.getValue());


        }
    }

    @Nested
    class getListPerson {
        @Test
        @DisplayName("Should get listPerson")
        void shouldGetListPerson() {

            var person = new Person(
                    UUID.randomUUID(),
                    "name person",
                    "birthdate date",
                    null
            );

            var personList = List.of(person);

            doReturn(personList).when(personRepository).findAll();

            var outPut = personService.getAll();

            assertEquals(personList.size(), outPut.size());

        }
    }

    @Nested
    class deleteById {

        @Test
        @DisplayName("Should delete person with success when person exists")
        void shouldDeleteByIdWithSuccessWhenPersonExists() {

            doReturn(true).when(personRepository).existsById(uuidArgumentCaptor.capture());

            doNothing().when(personRepository).deleteById(uuidArgumentCaptor.capture());

            var personId = UUID.randomUUID();

            personService.deleteById(personId.toString());

            var idList = uuidArgumentCaptor.getAllValues();
            assertEquals(personId, idList.get(0));
            assertEquals(personId, idList.get(1));

            verify(personRepository, times(1)).existsById(idList.get(0));

            verify(personRepository, times(1)).deleteById(idList.get(1));
        }

        @Test
        @DisplayName("Should not delete person when person not exists and verify exception")
        void shouldNotDeletePersonWhenNotExists() {

            doReturn(false).when(personRepository).existsById(uuidArgumentCaptor.capture());

            var personId = UUID.randomUUID();

            PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
                personService.deleteById(personId.toString());
            });

            assertTrue(exception.getMessage().contains(personId.toString()));

            assertEquals(personId, uuidArgumentCaptor.getValue());

            verify(personRepository, times(1)).existsById(uuidArgumentCaptor.getValue());

            verify(personRepository, times(0)).deleteById(any());
        }
    }

    @Nested
    class updatePersonById {
        @Test
        @DisplayName("Should update person by id when name and birthdate is filled")
        void shouldUpdatePersonByIdWhenNameAndBirthdateIsFilled() {

            var updatePerson = new PersonDto("name person", "birthdate date");

            var person = new Person(
                    UUID.randomUUID(),
                    "name person",
                    "birthdate date",
                    null
            );

            doReturn(Optional.of(person)).when(personRepository).findById(uuidArgumentCaptor.capture());


            doReturn(person).when(personRepository).save(personArgumentCaptor.capture());


            personService.updatePerson(person.getId().toString(), updatePerson);


            assertEquals(person.getId(), uuidArgumentCaptor.getValue());

            var userCaptured = personArgumentCaptor.getValue();

            assertEquals(updatePerson.name(), userCaptured.getName());
            assertEquals(updatePerson.birthdate(), userCaptured.getBirthdate());

            verify(personRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(personRepository, times(1)).save(person);


        }

        @Test
        @DisplayName("Should not update person when name not exists and verify exception")
        void shouldNotUpdatePersonWhenNameNotExists() {


            var updateDto = new PersonDto("name person", "birthdate person");


            doReturn(Optional.empty()).when(personRepository).findById(uuidArgumentCaptor.capture());

            var userId = UUID.randomUUID();

            PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
                personService.updatePerson(userId.toString(), updateDto);
            });

            assertTrue(exception.getMessage().contains(userId.toString()));

            assertEquals(userId, uuidArgumentCaptor.getValue());

            verify(personRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(personRepository, times(0)).save(any());
        }
    }
}