package com.danilo.elasticsearch.service.elasticsearch;

import com.danilo.elasticsearch.domain.db.Bank;
import com.danilo.elasticsearch.domain.elasticsearch.BankElasticsearch;
import com.danilo.elasticsearch.repository.db.BankDbRepository;
import com.danilo.elasticsearch.repository.elasticsearch.BankElasticsearchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankElasticsearchService {

    @Autowired
    private BankDbRepository repositoryDb;

    @Autowired
    private BankElasticsearchRepository repositoryElasticSearch;

    @Scheduled(fixedRate = 30000)
    public void syncronize() {
        List<Bank> banks = this.repositoryDb.findAll();

        banks.forEach((b) -> {
            BankElasticsearch bank = new BankElasticsearch();
            BeanUtils.copyProperties(b,bank);
            this.repositoryElasticSearch.save(bank);
        });

    }

    public Bank findOne(String id) {
        Optional<BankElasticsearch> b = this.repositoryElasticSearch.findById(id);

        if (b.isEmpty()) {
            String msg = "Bank not registered in the database.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }

        Bank bank = new Bank();
        BeanUtils.copyProperties(b.get(),bank);
        return bank;
    }

    public List<Bank> findAll() {
        List<Bank> banks = new ArrayList<>();

        if (this.repositoryElasticSearch.count() == 0) {
            String msg = "There are no banks registered in the database.";
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,msg);
        }

        this.repositoryElasticSearch.findAll().forEach((b) -> {
            Bank bank = new Bank();
            BeanUtils.copyProperties(b,bank);
            banks.add(bank);
        });

        return banks;
    }

}
