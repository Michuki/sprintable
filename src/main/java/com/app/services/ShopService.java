package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.assemblers.ShopResourceAssembler;
import com.app.model.Item;
import com.app.repository.ProductRepository;

import util.interfaces.BaseService;

@Service
public class ShopService implements BaseService<Long, String>{

	@Autowired
	ProductRepository plantRepo;
	
	private ShopResourceAssembler plantResourceAssembler = new ShopResourceAssembler();
	
	@Override
	public String createEntity(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		Item plant = plantRepo.findOne(id);
		plantRepo.saveAndFlush(plant);
	}

	@Override
	public String updateEntity(Long id, String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSingleEntity(Long id) {
		return plantResourceAssembler.toResourceString(plantRepo.findOne(id));
	}

	@Override
	public String getAllEntities() {
		return plantResourceAssembler.toResourcesString(plantRepo.findAll());
	}
}
