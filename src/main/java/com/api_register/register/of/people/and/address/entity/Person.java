package com.api_register.register.of.people.and.address.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_person")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String name;
    private String birthdate;


    @ManyToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    public Person() {
    }

    public Person(UUID id, String name, String birthdate, Address address) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
