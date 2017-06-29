package util;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.h2.value.DataType;
import org.springframework.hateoas.core.AnnotationMappingDiscoverer;
import org.springframework.hateoas.core.MappingDiscoverer;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import net.hamnaberg.json.Collection;
import net.hamnaberg.json.Collection.Builder;
import net.hamnaberg.json.Command;
import net.hamnaberg.json.Error;
import net.hamnaberg.json.Item;
import net.hamnaberg.json.Link;
import net.hamnaberg.json.Property;
import net.hamnaberg.json.Query;
import net.hamnaberg.json.Template;
import net.hamnaberg.json.URITarget;
import net.hamnaberg.json.Value;
import util.annotations.CJCommand;
import util.annotations.CJLink;
import util.annotations.CJQuery;
import util.annotations.CJson;
import util.annotations.CJsonRel;

public abstract class ResourceSupport<T> {
	static final String SLASH = "/";
	private static final MappingDiscoverer DISCOVERER = new AnnotationMappingDiscoverer(
			RequestMapping.class);

	public static java.util.Collection<Class> getClasses(final String pack)
			throws Exception {
		final StandardJavaFileManager fileManager = ToolProvider
				.getSystemJavaCompiler()
				.getStandardFileManager(null, null, null);
		return StreamSupport
				.stream(fileManager
						.list(StandardLocation.CLASS_PATH, pack,
								java.util.Collections.singleton(
										JavaFileObject.Kind.CLASS),
								false)
						.spliterator(), false)
				.map(javaFileObject -> {
					try {
						final String[] split = javaFileObject.getName()
								.replace(".class", "").replace(")", "")
								.split(Pattern.quote(File.separator));

						final String fullClassName = pack + "."
								+ split[split.length - 1];
						return Class.forName(fullClassName);
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(e);
					}

				}).collect(Collectors.toCollection(ArrayList::new));
	}

	public static URI getControllerURI(Class<?> controllerclass) {
		URI controllerUrl = null;
		try {
			controllerUrl = new URI("http://localhost:8686");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		try {
			controllerUrl = new URI(linkTo(controllerclass.getClass()).toUri()
					+ DISCOVERER.getMapping(controllerclass).toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return controllerUrl;
	}

	public static List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		try {
			java.util.Collection<Class> controllers = getClasses(
					"com.app.controllers");
			for (Class<?> controller : controllers) {
				Annotation annotation = controller.getAnnotation(CJLink.class);
				CJLink cjlink = null;
				if (annotation != null)
					cjlink = (CJLink) annotation;
				Link linkA = Link.create(getControllerURI(controller),
						cjlink.rel(), Optional.of(cjlink.prompt()),
						Optional.of(cjlink.name()));
				links.add(linkA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}

	private Class<?> controllerClass;

	private Builder collectionBuilder;

	public ResourceSupport(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Builder addCommands(String precondition) throws URISyntaxException, NoSuchFieldException, SecurityException {
		List<Command> commands = new ArrayList<Command>();
		for (CJCommand cjcommand : this.getClass()
				.getAnnotationsByType(CJCommand.class)) {
			for (String condition : cjcommand.precondition()) {
				if (condition.equals(precondition)) {
					List<Property> properties = new ArrayList<Property>();
					for (CJson cJson : cjcommand.data()) {
						properties.add(Property.value(
								(((cJson.name() == null) ? "name"
										: cJson.name())),
								Optional.of(((cJson.prompt() == null) ? "name"
										: cJson.prompt())),
								Value.of("")));
					}
					String url = getControllerURI() + SLASH + getId().toString();
					// + cjcommand.href();
					Command command = Command.create(
							new URITarget(new URI(url)), 
							cjcommand.rel(),
							Optional.of(cjcommand.prompt()),
							Optional.of(cjcommand.name()),
							Optional.of(cjcommand.method()), properties);
					commands.add(command);
				}
			}
		}
		return collectionBuilder.addCommands(commands);
	}

	public Iterable<Query> addQueries() throws IllegalArgumentException,
			IllegalAccessException, URISyntaxException {
		List<Query> queries = new ArrayList<Query>();
		for (CJQuery cjquery : this.getClass()
				.getAnnotationsByType(CJQuery.class)) {
			List<Property> properties = new ArrayList<Property>();
			for (CJson cJson : cjquery.data()) {
				properties
						.add(Property.value(
								(((cJson.name() == null) ? "name"
										: cJson.name())),
								Optional.of(((cJson.prompt() == null) ? "name"
										: cJson.prompt())),
								Value.of("")));
			}
			Query query = Query.create(getControllerURI(), cjquery.rel(),
					Optional.of(cjquery.prompt()), properties);
			queries.add(query);
		}
		return queries;
	}

	public Item asItem() throws URISyntaxException, IllegalAccessException {
		List<Property> properties = new ArrayList<>();
		List<Link> subresources = new ArrayList<>();
		for (Field field : this.getClass().getDeclaredFields()) {
			CJson cJson = field.getAnnotation(CJson.class);
			CJsonRel cJsonRel = field.getAnnotation(CJsonRel.class);
			if (field.getAnnotation(CJsonRel.class) != null) {
				ResourceSupport<T> subres = (ResourceSupport<T>) field
						.get(this);
				if (subres != null) {
					Link link = Link.create(subres.getLink(), cJsonRel.rel(),
							Optional.of(cJsonRel.prompt()),
							Optional.of(cJsonRel.name()));
					subresources.add(link);
					if ((cJsonRel.access() == Access.AUTO
							|| cJsonRel.access() == Access.READ_WRITE)) {
						properties.add(Property.value(
								(((cJsonRel.name() == null) ? field.getName()
										: cJsonRel.name())),
								Optional.of(((cJsonRel.prompt() == null)
										? field.getName() : cJsonRel.prompt())),
								Value.of(subres.getId().toString())));
					}
				}
			} else if (field.getAnnotation(CJson.class) != null) {
				properties.add(Property.value(
						(((cJson.name() == null) ? field.getName()
								: cJson.name())),
						Optional.of(((cJson.prompt() == null) ? field.getName()
								: cJson.prompt())),
						Value.of(field.get(this).toString())));
			}
		}
		return Item.create(getLink(), properties, subresources);
	}

	public Template asTemplate()
			throws IllegalArgumentException, IllegalAccessException {
		List<Property> properties = new ArrayList<Property>();
		for (Field field : this.getClass().getDeclaredFields()) {
			CJson cJson = field.getAnnotation(CJson.class);
			CJsonRel cJsonRel = field.getAnnotation(CJsonRel.class);
			if (field.getAnnotation(CJson.class) != null
					&& (cJson.access() == Access.AUTO
							|| cJson.access() == Access.READ_WRITE)) {
				properties.add(Property.value(
						(((cJson.name() == null) ? field.getName()
								: cJson.name())),
						Optional.of(((cJson.prompt() == null) ? field.getName()
								: cJson.prompt())),
						Value.of("")));
			} else if (field.getAnnotation(CJsonRel.class) != null
					&& (cJsonRel.access() == Access.AUTO
							|| cJsonRel.access() == Access.READ_WRITE)) {
				properties.add(Property.value(
						(((cJsonRel.name() == null) ? field.getName()
								: cJsonRel.name())),
						Optional.of(((cJsonRel.prompt() == null)
								? field.getName() : cJsonRel.prompt())),
						Value.of("")));
			}
		}
		return Template.create(properties);
	}

	public Builder addError(Error error) {
		return collectionBuilder.withError(error);
	}

	public Collection buildCollection() {
		return collectionBuilder.build();
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public URI getControllerURI() throws URISyntaxException {
		URI controllerUrl = new URI(linkTo(controllerClass.getClass()).toUri()
				+ DISCOVERER.getMapping(controllerClass).toString());
		return controllerUrl;
	}

	public abstract T getId();

	public URI getLink() throws URISyntaxException {
		URI link = new URI(getControllerURI() + SLASH + getId().toString());
		return link;
	}

	// Main builder
	public Builder toCollection() throws IllegalArgumentException,
			IllegalAccessException, URISyntaxException {
		collectionBuilder = Collection.builder(getControllerURI())
				.withTemplate(asTemplate()).addItem(asItem())
				.addLinks(getLinks());
		return collectionBuilder;
	}

	public Builder toCollection(List<Item> items)
			throws IllegalArgumentException, IllegalAccessException,
			URISyntaxException {
		return collectionBuilder = Collection.builder(getControllerURI())
				.withTemplate(asTemplate()).addLinks(getLinks()).addItems(items)
				.addQueries(addQueries());
	}
}
