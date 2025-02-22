package com.api_register.register.of.people.and.address.controller;

import com.api_register.register.of.people.and.address.dto.AddressDto;
import com.api_register.register.of.people.and.address.dto.PersonAddressDto;
import com.api_register.register.of.people.and.address.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/address")
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("{personId}")
    public ResponseEntity<PersonAddressDto> save(@PathVariable("personId") String personId, @RequestBody AddressDto addressDto) {

        var response = addressService.associateAddress(personId, addressDto);

        return ResponseEntity.ok(response);
    }
}
