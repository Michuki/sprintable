package com.app.resource;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.app.controllers.PurchaseOrderController;
import com.app.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import util.ResourceSupport;
import util.annotations.CJCommand;
import util.annotations.CJQuery;
import util.annotations.CJson;
import util.annotations.CJsonRel;

@Getter
@Setter
@CJQuery(rel = "filterByEndDate", prompt = "Filter by endDate", data = {
		@CJson(name = "endDate", prompt = "End Date", access = Access.AUTO) })
@CJQuery(rel = "sortByEndDate", prompt = "Sort By endDate", data = {
		@CJson(name = "endDate", prompt = "End Date", access = Access.AUTO) })
@CJCommand(precondition = "PENDING", name = "Approve", prompt = "Approve PO", rel = "acceptPurchaseOrder", data = {}, method = "put")
@CJCommand(precondition = "PENDING", name = "Reject", prompt = "Reject PO", rel = "rejectPurchaseOrder", data = {}, method = "put")
@CJCommand(precondition = "APPROVED", name = "Close", prompt = "Close PO", rel = "closePurchaseOrder", data = {}, method = "put")
@CJCommand(precondition = "RETURNED", name = "Invoice", prompt = "Invoice PO", rel = "confirmInvoiceSent", data = {}, method = "put")
@CJCommand(precondition = "DISPATCHED", name = "Deliver", prompt = "Confirm Conference", rel = "deliverConference", data = {}, method = "put")
@CJCommand(precondition = "APPROVED", name = "Dispatch", prompt = "Book Conference", rel = "dispatchConference", data = {}, method = "put")
@CJCommand(precondition = "DISPATCHED", name = "Reject", prompt = "Reject Conference", rel = "rejectConference", data = {}, method = "put")
@CJCommand(precondition = { "DELIVERED",
"REJECTED" }, name = "Return", prompt = "Return PurchaseOrder", rel = "returnConference", data = {}, method = "put")
@CJCommand(precondition = "APPROVED", name = "Update", prompt = "Update PO", rel = "requestExtension", data = {
		@CJson(access = Access.AUTO, name = "startDate", prompt = "Start Date"),
		@CJson(access = Access.AUTO, name = "endDate", prompt = "End Date") }, method = "put")
public class PurchaseOrderResource extends ResourceSupport<Long> {
	public PurchaseOrderResource() {
		super(PurchaseOrderController.class);
	}

	@CJson(name = "id", prompt = "ID", access = Access.READ_ONLY)
	public Long id;

	@CJsonRel(name = "Conference", prompt = "Conference", rel = "Conference", access = Access.AUTO)
	public ShopResource conference;

	@CJson(name = "startDate", prompt = "Start Date", access = Access.AUTO)
	public LocalDate startDate;

	@CJson(name = "endDate", prompt = "End Date", access = Access.AUTO)
	public LocalDate endDate;

	@CJson(name = "createdDate", prompt = "Created Date", access = Access.AUTO)
	public LocalDate createdDate;

	@CJson(name = "cost", prompt = "Cost", access = Access.AUTO)
	public BigDecimal cost;

	@CJson(name = "status", prompt = "Status", access = Access.READ_WRITE)
	public Status status;

	public Long getId() {
		return id;
	}
}