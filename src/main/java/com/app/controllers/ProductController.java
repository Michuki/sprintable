package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.ProductService;

import util.GenericController;
import util.annotations.CJLink;

@RestController
@RequestMapping("/api/products")
@CJLink(name = "Product", prompt = "Products", rel = "Products", href = "/api/products")
public class ProductController extends GenericController<Long, String> {

	@Autowired
	public ProductController(ProductService service) {
		super(service);
	}
}
