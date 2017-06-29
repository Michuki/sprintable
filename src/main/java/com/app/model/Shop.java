package com.app.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Shop extends PersistableEntity{

	int seatNumber;
	String name;
	String address;
	String location;
	BigDecimal cost;
	
	@OneToOne
	User user;
	
	public enum SeatType {
		VIP, REGULAR
	}
}