package com.danilo.elasticsearch.controller;

import com.danilo.elasticsearch.domain.db.Bank;
import com.danilo.elasticsearch.dto.BankDTO;
import com.danilo.elasticsearch.service.db.BankDbService;
import com.danilo.elasticsearch.service.elasticsearch.BankElasticsearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

    @Autowired
    private BankDbService serviceDb;

    @Autowired
    private BankElasticsearchService serviceElasticsearch;

    @GetMapping
    public ResponseEntity<List<Bank>> findAll() {
        List<Bank> banks = this.serviceElasticsearch.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(banks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> findOne(@PathVariable String id) {
        Bank bank = this.serviceElasticsearch.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(bank);
    }

    @PostMapping
    public ResponseEntity<Bank> insert(@RequestBody @Valid BankDTO dto) {
        Bank bank = this.serviceDb.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bank);
    }

    @PutMapping
    public ResponseEntity<Bank> update(@RequestBody @Valid BankDTO dto) {
        Bank bank = this.serviceDb.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        this.serviceDb.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
