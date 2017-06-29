package com.app.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.assemblers.PurchaseOrderResourceAssembler;
import com.app.exceptions.ProductNotAvailableException;
import com.app.exceptions.PONotFoundException;
import com.app.model.Item;
import com.app.model.Product;
import com.app.model.PurchaseOrder;
import com.app.model.Status;
import com.app.repository.ProductRepository;
import com.app.repository.PurchaseOrderRepository;
import com.app.resource.PurchaseOrderResource;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamnaberg.json.Property;
import util.annotations.CJCommand;
import util.annotations.CJson;
import util.interfaces.BaseService;

@Service
public class PurchaseOrderService implements BaseService<Long, String> {

	@Autowired
	ProductRepository conferenceRepo;

	@Autowired
	PurchaseOrderRepository poRepo;

	public static PurchaseOrderResourceAssembler purchaseOrderResourceAssembler = new PurchaseOrderResourceAssembler();

	@CJCommand(precondition = "PENDING", name = "Approve", prompt = "Approve PO", rel = "acceptPurchaseOrder", data = {}, method = "put")
	public PurchaseOrder acceptPurchaseOrder(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.APPROVED);
		poRepo.save(po);
		return po;
	}

	@CJCommand(precondition = "PENDING", name = "Reject", prompt = "Reject PO", rel = "rejectPurchaseOrder", data = {}, method = "put")
	public PurchaseOrder rejectPurchaseOrder(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.REJECTED);
		poRepo.save(po);
		return po;
	}

	public static int getDateDifference(LocalDate localDate,
			LocalDate localDate2) {
		return Period.between(localDate, localDate2).getDays();
	}

	@CJCommand(precondition = "APPROVED", name = "Close", prompt = "Close PO", rel = "closePurchaseOrder", data = {}, method = "put")
	public PurchaseOrder closePurchaseOrder(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.CLOSED);
		poRepo.save(po);
		return po;
	}

	@CJCommand(precondition = "RETURNED", name = "Invoice", prompt = "Invoice PO", rel = "confirmInvoiceSent", data = {}, method = "put")
	public PurchaseOrder confirmInvoiceSent(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.INVOICED);
		poRepo.save(po);
		return po;
	}

	public PurchaseOrder createPurchaseOrder(PurchaseOrderResource poResource)
			throws ProductNotAvailableException {
		PurchaseOrder po = new PurchaseOrder();
		po.setStartDate(po.getStartDate());
		po.setEndDate(po.getEndDate());
		BigDecimal difference = new BigDecimal(
				getDateDifference(po.getStartDate(), po.getEndDate()));
		Product product;
		po.setCost(100);
		po.setStatus(Status.PENDING);
		po = poRepo.saveAndFlush(po);
		return po;
	}

	@CJCommand(precondition = "DISPATCHED", name = "Deliver", prompt = "Confirm Conference", rel = "deliverConference", data = {}, method = "put")
	public PurchaseOrder deliverConference(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.COMPLETED);
		poRepo.save(po);
		return po;
	}

	@CJCommand(precondition = "APPROVED", name = "Dispatch", prompt = "Book Conference", rel = "dispatchConference", data = {}, method = "put")
	public PurchaseOrder dispatchConference(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.APPROVED);
		poRepo.save(po);
		return po;
	}

	public List<PurchaseOrder> getAll() {
		return poRepo.findAll();
	}

	public PurchaseOrder getPurchaseOrder(Long id) throws PONotFoundException {
		PurchaseOrder po = poRepo.findOne(id);
		if (po == null)
			throw new PONotFoundException(id);
		return po;
	}

	@CJCommand(precondition = "DISPATCHED", name = "Reject", prompt = "Reject Conference", rel = "rejectConference", data = {}, method = "put")
	public PurchaseOrder rejectConference(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.REJECTED);
		poRepo.save(po);
		return po;
	}

	@CJCommand(precondition = { "DELIVERED",
			"REJECTED" }, name = "Return", prompt = "Return PurchaseOrder", rel = "returnConference", data = {}, method = "put")
	public PurchaseOrder returnConference(Long id) {
		PurchaseOrder po = poRepo.findOne(id);
		po.setStatus(Status.COMPLETED);
		poRepo.saveAndFlush(po);
		return po;
	}

	@CJCommand(precondition = "APPROVED", name = "Update", prompt = "Update PO", rel = "requestExtension", data = {
			@CJson(access = Access.AUTO, name = "startDate", prompt = "Start Date"),
			@CJson(access = Access.AUTO, name = "endDate", prompt = "End Date") }, method = "put")
	public String requestExtension(Long id, String collection)
			throws ProductNotAvailableException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Property> properties;
		PurchaseOrder po = poRepo.findOne(id);
//		Conference conference = conferenceRepo.finAvailablebyIdAndbetweenDates(
//				po.getConference().getId(), po.getStartDate(), po.getEndDate());
//		if (conference == null) {
//			throw new ConferenceNotAvailableException(
//					po.getConference().getId());
//		} else {
//			try {
//				properties = CollectionParserHelper.getProperties(collection);
//				po.setEndDate(CollectionParserHelper.getLocalDate(properties,
//						"endDate"));
//				po.setStartDate(CollectionParserHelper.getLocalDate(properties,
//						"startDate"));
//				po.setStatus(Status.CLOSED);
//				poRepo.saveAndFlush(po);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		//PurchaseOrder newPo = new PurchaseOrder();
		//newPo.setConference(conference);
		po.setStartDate(po.getStartDate());
		po.setEndDate(po.getEndDate());
		BigDecimal difference = new BigDecimal(
				getDateDifference(po.getStartDate(), po.getEndDate()));
		//BigDecimal cost = conference.getPrice().multiply(difference);
		//po.setCost(cost);
		po.setStatus(Status.PENDING);
		poRepo.saveAndFlush(po);
		return purchaseOrderResourceAssembler.toResourceString(po);
	}

	public PurchaseOrder save(PurchaseOrder po) {
		poRepo.saveAndFlush(po);
		return po;
	}

	public PurchaseOrder findOne(Long id) {
		return poRepo.findOne(id);
	}

	@Override
	public String createEntity(String collection) {
		PurchaseOrder po = new PurchaseOrder();
		try {
			po = purchaseOrderResourceAssembler.toModel(po, collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		poRepo.save(po);
		return purchaseOrderResourceAssembler.toResourceString(po);
	}

	@Override
	public void deleteEntity(Long id) {
		poRepo.delete(id);
	}

	@Override
	public String updateEntity(Long id, String collection) {
		PurchaseOrder po = poRepo.findOne(id);
		try {
			po = purchaseOrderResourceAssembler.toModel(po, collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		poRepo.save(po);
		return purchaseOrderResourceAssembler.toResourceString(po);
	}

	@Override
	public String getSingleEntity(Long id) {
		return purchaseOrderResourceAssembler
				.toResourceString(poRepo.findOne(id));
	}

	@Override
	public String getAllEntities() {
		return purchaseOrderResourceAssembler
				.toResourcesString(poRepo.findAll());
	}
}
