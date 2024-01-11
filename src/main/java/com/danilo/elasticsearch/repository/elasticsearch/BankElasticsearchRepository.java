package com.danilo.elasticsearch.repository.elasticsearch;

import com.danilo.elasticsearch.domain.elasticsearch.BankElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankElasticsearchRepository extends ElasticsearchRepository<BankElasticsearch,String> {

}
