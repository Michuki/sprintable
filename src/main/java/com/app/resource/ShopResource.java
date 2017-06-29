package com.app.resource;
import java.math.BigDecimal;

import com.app.controllers.ShopController;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import util.ResourceSupport;
import util.annotations.CJson;

@Getter
@Setter
public class ShopResource extends ResourceSupport <Long> {
	public ShopResource() {
		super(ShopController.class);
	}
	@CJson(name = "id", prompt = "ID", access = Access.READ_ONLY )
	public Long id;
	
	@CJson(name = "name", prompt = "Name", access = Access.AUTO)
    public String name;
	
	@CJson(name = "description", prompt = "Description", access = Access.AUTO)
    public String description;
	
	@CJson(name = "price", prompt = "Price", access = Access.AUTO)
    public BigDecimal price;

	public Long getId() {
		return id;
	}
}