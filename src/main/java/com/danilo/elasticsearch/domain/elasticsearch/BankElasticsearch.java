package com.danilo.elasticsearch.domain.elasticsearch;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bank")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class BankElasticsearch {

    @Id
    private String id;

    private String name;

    private String code;

    private String agency;

}
