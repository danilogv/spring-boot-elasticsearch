package com.danilo.elasticsearch.repository;

import com.danilo.elasticsearch.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,String> {

    Boolean existsBankByCodeAndAgency(String code,String agency);

}
