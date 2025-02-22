package com.api_register.register.of.people.and.address.service;

import com.api_register.register.of.people.and.address.dto.PersonDto;
import com.api_register.register.of.people.and.address.entity.Person;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

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
}