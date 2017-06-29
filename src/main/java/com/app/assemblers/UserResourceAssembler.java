package com.app.assemblers;

import com.app.controllers.UserController;
import com.app.model.User;
import com.app.resource.UserResource;

import lombok.EqualsAndHashCode;
import util.ResourceAssemblerSupport;

@EqualsAndHashCode(callSuper = false)
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}
	@Override
	public UserResource toResource(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toResourceString(User entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toResourcesString(Iterable<User> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toModel(User entity, String collection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
