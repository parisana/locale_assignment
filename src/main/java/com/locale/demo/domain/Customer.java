package com.locale.demo.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author Parisana
 */
@Entity
@RequiredArgsConstructor
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final String name;

    private Customer(){
        this.id = null;
        this.name = null;
    }

}
