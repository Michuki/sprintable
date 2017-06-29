package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.ShopService;

import util.GenericController;
import util.annotations.CJLink;

@RestController
@RequestMapping("/api/shops")
@CJLink(name = "Shops", prompt = "Shops", rel = "shops",href="/api/shops")
public class ShopController extends GenericController<Long, String>{
	
	@Autowired
	public ShopController(ShopService service) {
		super(service);
	}
}
