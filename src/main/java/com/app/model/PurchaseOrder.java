package com.app.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class PurchaseOrder extends PersistableEntity{

	@OneToMany
	List<OrderItem> orderItems;
	LocalDate startDate;
	LocalDate endDate;
	@Enumerated(EnumType.STRING)
	Status status;
	LocalDate createdDate;
	@Column(precision = 8, scale = 2)
    int cost;	
}
