package com.app.assemblers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Map;

import com.app.controllers.ShopController;
import com.app.model.Item;
import com.app.resource.ShopResource;

import net.hamnaberg.json.Property;
import util.CollectionParserHelper;
import util.ResourceAssemblerSupport;

public class ShopResourceAssembler
		extends ResourceAssemblerSupport<Item, ShopResource> {

	public ShopResourceAssembler() {
		super(ShopController.class, ShopResource.class);
	}

	@Override
	public Item toModel(Item conference, String Collection)
			throws IOException {
		Map<String, Property> properties = CollectionParserHelper
				.getProperties(Collection);
		conference.setPrice(new BigDecimal(
				CollectionParserHelper.getPropertyValue(properties, "cost")));
		conference.setDescription(CollectionParserHelper
				.getPropertyValue(properties, "description"));
		conference.setName(
				CollectionParserHelper.getPropertyValue(properties, "name"));
		conference.setPrice(new BigDecimal(
				CollectionParserHelper.getPropertyValue(properties, "name")));
		return conference;
	}

	public ShopResource toResource(Item conference) {
		if (conference != null) {
			ShopResource entity = createResourceWithId(conference.getId(),conference);
			entity.setId(conference.getId());
			entity.setName(conference.getName());
			entity.setDescription(conference.getDescription());
			entity.setPrice(conference.getPrice());
			return entity;
		} else {
			return null;
		}
	}

	@Override
	public String toResourceString(Item conference) {
		ShopResource conferenceResource = toResource(conference);
		try {
			conferenceResource.toCollection();
		} catch (IllegalArgumentException | IllegalAccessException
				| URISyntaxException e) {
			e.printStackTrace();
		}
		return conferenceResource.buildCollection().toString();
	}

	@Override
	public String toResourcesString(Iterable<Item> conferences) {
		ShopResource conferenceResource = new ShopResource();
		try {
			conferenceResource.toCollection(toItems(conferences));
		} catch (IllegalArgumentException | IllegalAccessException
				| URISyntaxException e) {
			e.printStackTrace();
		}
		return conferenceResource.buildCollection().toString();
	}
}
