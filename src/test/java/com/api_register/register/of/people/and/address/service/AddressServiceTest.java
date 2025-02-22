package com.api_register.register.of.people.and.address.service;

import com.api_register.register.of.people.and.address.dto.AddressDto;
import com.api_register.register.of.people.and.address.dto.PersonAddressDto;
import com.api_register.register.of.people.and.address.entity.Address;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.repository.AddressRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;


    @Captor
    private ArgumentCaptor<Person> personArgumentCaptor;


    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class associateAddress {
        @Test
        @DisplayName("should associate person with success")
        void associateAddressWithPerson() {

            UUID personId = UUID.randomUUID();
            String personIdStr = personId.toString();
            AddressDto addressDto = new AddressDto("city", "street", "zipCode", "number");

            Person person = new Person(null, "name person", "birthdate person", null);

            when(personRepository.findById(personId))
                    .thenReturn(Optional.of(person));

            when(addressRepository.findByCityAndStreetAndZipCodeAndNumber(
                    addressDto.city(), addressDto.street(), addressDto.zipCode(), addressDto.number()))
                    .thenReturn(Optional.empty());

            Address newAddress = new Address();
            newAddress.setCity(addressDto.city());
            newAddress.setStreet(addressDto.street());
            newAddress.setZipCode(addressDto.zipCode());
            newAddress.setNumber(addressDto.number());

            when(addressRepository.save(any(Address.class)))
                    .thenReturn(newAddress);


            when(personRepository.save(any(Person.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            PersonAddressDto result = addressService.associateAddress(personIdStr, addressDto);

            assertNotNull(result);
            assertEquals("name person", result.name());
            assertEquals("birthdate person", result.birthdate());

            AddressDto resultAddress = result.address();
            assertNotNull(resultAddress);
            assertEquals("city", resultAddress.city());
            assertEquals("street", resultAddress.street());
            assertEquals("zipCode", resultAddress.zipCode());
            assertEquals("number", resultAddress.number());

            verify(personRepository).save(any(Person.class));
        }
    }

}