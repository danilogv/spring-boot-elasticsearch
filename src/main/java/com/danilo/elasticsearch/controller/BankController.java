package com.danilo.elasticsearch.controller;

import com.danilo.elasticsearch.domain.Bank;
import com.danilo.elasticsearch.dto.BankDTO;
import com.danilo.elasticsearch.service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

    @Autowired
    private BankService service;

    @PostMapping
    public ResponseEntity<Bank> insert(@RequestBody @Valid BankDTO dto) {
        Bank bank = this.service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bank);
    }

    @PutMapping
    public ResponseEntity<Bank> update(@RequestBody @Valid BankDTO dto) {
        Bank bank = this.service.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        this.service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
