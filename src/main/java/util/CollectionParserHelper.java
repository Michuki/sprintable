package util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import net.hamnaberg.json.Property;
import net.hamnaberg.json.Template;
import net.hamnaberg.json.Value;
import net.hamnaberg.json.Value.StringValue;
import net.hamnaberg.json.parser.CollectionParser;

public class CollectionParserHelper {
	public static LocalDate getLocalDate(Map<String, Property> properties,
			String name) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(getPropertyValue(properties, name), formatter);
	}

	public static Map<String, Property> getProperties(String Collection)
			throws IOException {
		Template template = new CollectionParser().parseTemplate(Collection);
		Map<String, Property> properties = template.getDataAsMap();
		return properties;
	}

	public static String getPropertyValue(Map<String, Property> properties,
			String name) {
		String valuename = "";
		Optional<Value> value = properties.get(name).getValue();
		if (value.isPresent())
			return ((StringValue)(properties.get(name).getValue()).get()).value;
		return valuename;
	}
}
