package com.api_register.register.of.people.and.address.repository;

import com.api_register.register.of.people.and.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByCityAndStreetAndZipCodeAndNumber(String city, String street, String zipCode, String number);
}
