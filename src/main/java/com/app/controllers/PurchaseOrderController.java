package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.PurchaseOrderService;

import util.GenericController;
import util.annotations.CJLink;

@RestController
@RequestMapping("/api/pos")
@CJLink(name = "PO", prompt = "Purchase Orders", rel = "POs", href = "/api/pos")
public class PurchaseOrderController extends GenericController<Long, String>{

	@Autowired
	public PurchaseOrderController(PurchaseOrderService service) {
		super(service);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/filterbyenddate")
	public String filterByName(@PathVariable String collection)
			throws Exception {
		System.out.println(collection);
		return getAllEntities();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sortbyenddate")
	public String sortByEndDate(
			@RequestParam(value = "endDate", required = false) String collection)
					throws Exception {
		System.out.println(collection);
		return getAllEntities();
	}
}
