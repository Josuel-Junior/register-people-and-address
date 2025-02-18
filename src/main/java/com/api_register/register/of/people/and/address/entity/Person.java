package com.api_register.register.of.people.and.address.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_person")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String name;
    private String birthDate;


    public Person() {
    }

    public Person(UUID id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
