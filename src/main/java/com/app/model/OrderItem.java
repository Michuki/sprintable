package com.app.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class OrderItem extends PersistableEntity{
	
	@OneToOne
	Product product;
	String description;
	BigDecimal totalCost;
	int quantity;
}