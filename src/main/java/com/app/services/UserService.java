package com.app.services;

import org.springframework.stereotype.Service;

import util.interfaces.BaseService;

@Service
public class UserService implements BaseService<Long, String> {

	@Override
	public String createEntity(String entity) {
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
	}

	@Override
	public String updateEntity(Long id, String entity) {
		return null;
	}

	@Override
	public String getSingleEntity(Long id) {
		return null;
	}

	@Override
	public String getAllEntities() {
		return null;
	}
}
