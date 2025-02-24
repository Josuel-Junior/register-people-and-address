package com.api_register.register.of.people.and.address.controller;


import com.api_register.register.of.people.and.address.dto.PersonDto;
import com.api_register.register.of.people.and.address.entity.Person;
import com.api_register.register.of.people.and.address.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {


    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Cria uma nova pessoa", description = "Cria uma nova pessoa a partir dos dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody PersonDto personDto) {
        var response = personService.createdPerson(personDto);
        return ResponseEntity.created(URI.create("./person/" + response.toString())).build();
    }

    @Operation(summary = "Lista todas as pessoas", description = "Retorna a lista de todas as pessoas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Person>> getAllPerons() {
        var responseList = personService.getAll();
        return ResponseEntity.ok(responseList);
    }

    @Operation(summary = "Exclui uma pessoa", description = "Exclui a pessoa identificada pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{personId}")
    public ResponseEntity<UUID> delete(
            @Parameter(description = "ID da pessoa a ser excluída")
            @PathVariable("personId") String personId) {
        personService.deleteById(personId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca uma pessoa por ID", description = "Retorna a pessoa correspondente ao ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{personId}")
    public ResponseEntity<Person> getById(
            @Parameter(description = "ID da pessoa a ser buscada")
            @PathVariable("personId") String personId) {
        var personOptional = personService.getById(personId);
        if (personOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(personOptional.get());
    }

    @Operation(summary = "Atualiza os dados de uma pessoa", description = "Atualiza os dados da pessoa identificada pelo ID com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{personId}")
    public ResponseEntity<Person> update(
            @Parameter(description = "ID da pessoa a ser atualizada")
            @PathVariable("personId") String personId,
            @RequestBody PersonDto personDto) {
        var responseUpdate = personService.updatePerson(personId, personDto);
        return ResponseEntity.ok(responseUpdate);
    }

}
