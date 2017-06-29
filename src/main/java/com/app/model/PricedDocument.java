package com.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class PricedDocument {
    @Id
    @GeneratedValue
    Long id;
    @Column(precision = 8, scale = 2)
    BigDecimal amount;
}