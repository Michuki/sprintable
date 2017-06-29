package com.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Invoice extends PersistableEntity{
	
	LocalDate issueDate;
	BigDecimal totalAmount;
	
	LocalDate paymentDueDate;
	@OneToMany
	List<PricedDocument> pricedDocuments;
	String invoiceBody;
	InvoiceStatus status;
	
	public enum InvoiceStatus {
		PENDING,
		APPROVED,
		REJECTED,
		DISPUTE
	}
}
