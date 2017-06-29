package com.app.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class PersistableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	public Long getId() {
		return id;
	}
}
