package com.api_register.register.of.people.and.address.controller;

import com.api_register.register.of.people.and.address.dto.AddressDto;
import com.api_register.register.of.people.and.address.dto.PersonAddressDto;
import com.api_register.register.of.people.and.address.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/address")
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
            summary = "Associa endereço à pessoa",
            description = "Associa um endereço à pessoa identificada pelo ID. Se o endereço já existir, ele é vinculado; caso contrário, é criado e associado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço associado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("{personId}")
    public ResponseEntity<PersonAddressDto> save(
            @Parameter(description = "ID da pessoa à qual o endereço será associado", required = true)
            @PathVariable("personId") String personId,
            @Parameter(description = "Dados do endereço a ser associado", required = true)
            @RequestBody AddressDto addressDto) {

        var response = addressService.associateAddress(personId, addressDto);
        return ResponseEntity.ok(response);
    }
}
