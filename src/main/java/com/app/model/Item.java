package com.app.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends PersistableEntity{
    
	String name;
    String description;
    @Column(precision = 8, scale = 2)
    BigDecimal price;
	@OneToMany
	List<Product> seats;
	
}
