package com.app.services;

import org.springframework.stereotype.Service;

import util.interfaces.BaseService;

@Service
public class ProductService implements BaseService<Long,String>{

	@Override
	public String createEntity(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public String updateEntity(Long id, String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSingleEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
