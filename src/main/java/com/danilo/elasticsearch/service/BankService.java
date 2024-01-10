package com.danilo.elasticsearch.service;

import com.danilo.elasticsearch.domain.Bank;
import com.danilo.elasticsearch.dto.BankDTO;
import com.danilo.elasticsearch.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository repository;

    @Transactional
    public Bank insert(BankDTO dto) {
        Boolean existsCodeAndAgency =  this.repository.existsBankByCodeAndAgency(dto.getCode(),dto.getAgency());

        if (existsCodeAndAgency) {
            String msg = "Code already registered with a bank.";
            throw new ResponseStatusException(HttpStatus.CONFLICT,msg);
        }

        Bank bank = new Bank();
        BeanUtils.copyProperties(dto,bank);
        return this.repository.save(bank);
    }

    @Transactional
    public Bank update(BankDTO dto) {
        Boolean existsId = this.repository.findById(dto.getId()).isPresent();

        if (!existsId) {
            String msg = "Bank not found in the database.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }

        Bank bank = new Bank();
        BeanUtils.copyProperties(dto,bank);
        return this.repository.save(bank);
    }

    @Transactional
    public void remove(String id) {
        Optional<Bank> bank = this.repository.findById(id);

        if (bank.isEmpty()) {
            String msg = "Bank not found in the database.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }

        this.repository.deleteById(id);
    }

}
