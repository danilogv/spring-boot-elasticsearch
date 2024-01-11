package com.danilo.elasticsearch.domain.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Entity
@Table(name = "bank")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"code"})
public class Bank implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "id",nullable = false,unique = true)
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @Column(name = "agency",nullable = false)
    private String agency;

}
