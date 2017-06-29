package com.app.resource;

import com.app.controllers.UserController;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import util.ResourceSupport;
import util.annotations.CJson;

@Getter
@Setter
public class UserResource extends ResourceSupport<Long> {
	public UserResource() {
		super(UserController.class);
	}

	@CJson(name = "id", prompt = "ID", access = Access.READ_ONLY )
	public Long id;
	
	@CJson(name = "name", prompt = "Name", access = Access.AUTO)
    public String name;
	
	@Override
	public Long getId() {
		return id;
	}
}
