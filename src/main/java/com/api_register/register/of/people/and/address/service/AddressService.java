package com.api_register.register.of.people.and.address.service;


import com.api_register.register.of.people.and.address.dto.AddressDto;
import com.api_register.register.of.people.and.address.dto.PersonAddressDto;
import com.api_register.register.of.people.and.address.entity.Address;
import com.api_register.register.of.people.and.address.exception.PersonNotFoundException;
import com.api_register.register.of.people.and.address.repository.AddressRepository;
import com.api_register.register.of.people.and.address.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {

    final AddressRepository addressRepository;

    final PersonRepository personRepository;

    public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    public PersonAddressDto associateAddress(String personId, AddressDto addressDto) {

        var id = UUID.fromString(personId);

        var personOptional = personRepository.findById(id);

        if (personOptional.isEmpty()) {
            throw new PersonNotFoundException("Pessoa com ID " + id + " não encontrada.");
        }


        var person = personOptional.get();

        var addressOptional = addressRepository.findByCityAndStreetAndZipCodeAndNumber(addressDto.city(), addressDto.street(), addressDto.zipCode(), addressDto.number());

        Address address;

        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            Address newAddress = new Address();
            newAddress.setCity(addressDto.city());
            newAddress.setStreet(addressDto.street());
            newAddress.setZipCode(addressDto.zipCode());
            newAddress.setNumber(addressDto.number());
            addressRepository.save(newAddress);

            address = addressRepository.save(newAddress);
        }

        person.setAddress(address);
        personRepository.save(person);

        return new PersonAddressDto(
                person.getName(),
                person.getBirthdate(),
                new AddressDto(address.getCity(), address.getStreet(), address.getZipCode(), address.getNumber())
        );

    }
}
