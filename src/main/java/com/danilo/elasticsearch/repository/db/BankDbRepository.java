package com.danilo.elasticsearch.repository.db;

import com.danilo.elasticsearch.domain.db.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDbRepository extends JpaRepository<Bank,String> {

    Boolean existsBankByCodeAndAgency(String code,String agency);

}
