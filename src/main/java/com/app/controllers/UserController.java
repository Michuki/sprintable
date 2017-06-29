package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.UserService;

import util.GenericController;
import util.annotations.CJLink;

@RestController
@RequestMapping("/api/users")
@CJLink(name = "Users", prompt = "Users", rel = "Users", href = "/api/users")
public class UserController extends GenericController<Long, String> {

	@Autowired
	public UserController(UserService service) {
		super(service);
	}
}
