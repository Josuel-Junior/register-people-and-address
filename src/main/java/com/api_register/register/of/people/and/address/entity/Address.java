package com.api_register.register.of.people.and.address.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_address")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String city;
    private String street;
    private String zipCode;
    private String number;

    public Address(UUID id, String city, String street, String zipCode, String number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
    }

    @OneToMany(mappedBy = "address")
    private List<Person> person;


    public Address() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
