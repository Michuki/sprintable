package com.app.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends PersistableEntity{
	
	String name;
    String description;
    String image;
    ProductType productType;
    
    public enum ProductType {
    	TSHIRT,
    	HOODIE,
    	VEST
    }
}
