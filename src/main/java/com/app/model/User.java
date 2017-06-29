package com.app.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends PersistableEntity {
	String firstName;
	String lastName;
	String address;
	String dateOfBirth;
	String mobileNumber;
	UserType userType;
	String userName;
	String password;
	String zipCode;
	String city;
	String createdDate;
	String modifiedDate;
	String location;
	
	public enum UserType {
		CUSTOMER,
		PRINTSHOP
	}
}
