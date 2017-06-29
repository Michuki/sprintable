package com.app.assemblers;

import java.net.URISyntaxException;
import java.util.Map;

import com.app.controllers.PurchaseOrderController;
import com.app.model.PurchaseOrder;
import com.app.model.Status;
import com.app.resource.PurchaseOrderResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.hamnaberg.json.Error;
import net.hamnaberg.json.Property;
import util.CollectionParserHelper;
import util.ResourceAssemblerSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderResourceAssembler
		extends ResourceAssemblerSupport<PurchaseOrder, PurchaseOrderResource> {

	private Error error;

	public PurchaseOrderResourceAssembler() {
		super(PurchaseOrderController.class, PurchaseOrderResource.class);
	}

	private static final ShopResourceAssembler productResourceAssembler = new ShopResourceAssembler();

	@Override
	public PurchaseOrder toModel(PurchaseOrder entity, String Collection)
			throws Exception {
		Map<String, Property> properties = CollectionParserHelper
				.getProperties(Collection);
		entity.setCost(new Integer(
				CollectionParserHelper.getPropertyValue(properties, "cost")));
		entity.setStartDate(
				CollectionParserHelper.getLocalDate(properties, "startDate"));
		entity.setEndDate(
				CollectionParserHelper.getLocalDate(properties, "endDate"));
		entity.setCreatedDate(
				CollectionParserHelper.getLocalDate(properties, "createdDate"));
		entity.setStatus(Status.valueOf(
				CollectionParserHelper.getPropertyValue(properties, "status")));
		return entity;
	}

	public String toResourceString(PurchaseOrder po) {
		PurchaseOrderResource poResource = toResource(po);
		try {
			poResource.toCollection();
		} catch (IllegalArgumentException | IllegalAccessException
				| URISyntaxException error) {
			poResource.addError(addError(error));
			error.printStackTrace();
		}
		try {
			poResource.addCommands(po.getStatus().toString());
		} catch (Exception error) {
			poResource.addError(addError(error));

		}
		return poResource.buildCollection().toString();
	}

	public Error addError(Exception exception) {
		if(exception != null)
			error = Error.create(exception.getMessage(),
					exception.getLocalizedMessage(), exception.getMessage());
		return error;
	}

	@Override
	public PurchaseOrderResource toResource(PurchaseOrder po) {
		PurchaseOrderResource poResource = createResourceWithId(po.getId(), po);
		try {
			poResource.setId(po.getId());
			poResource.setEndDate(po.getEndDate());
			poResource.setStartDate(po.getStartDate());
			poResource.setCreatedDate(po.getCreatedDate());
			poResource.setStatus(po.getStatus());
		} catch (Exception error) {
			poResource.addError(addError(error));
			error.printStackTrace();
		}
		return poResource;
	}

	@Override
	public String toResourcesString(Iterable<PurchaseOrder> allPos) {
		PurchaseOrderResource poResource = new PurchaseOrderResource();
		try {
			poResource.toCollection(toItems(allPos));
		} catch (IllegalArgumentException | IllegalAccessException
				| URISyntaxException error) {
			poResource.addError(addError(error));
			error.printStackTrace();
		}
		return poResource.buildCollection().toString();
	}
}
