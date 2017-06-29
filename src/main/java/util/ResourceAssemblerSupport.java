package util;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import net.hamnaberg.json.Item;
import util.interfaces.ResourceAssembler;

public abstract class ResourceAssemblerSupport<T, D extends ResourceSupport<?>>
		implements ResourceAssembler<T, D> {
	private final Class<?> controllerClass;
	private final Class<D> resourceType;

	/**
	 * Creates a new {@link ResourceAssemblerSupport} using the given controller
	 * class and resource type.
	 * 
	 * @param controllerClass
	 * must not be {@literal null}.
	 * @param resourceType
	 * must not be {@literal null}.
	 */
	public ResourceAssemblerSupport(Class<?> controllerClass,
			Class<D> resourceType) {

		Assert.notNull(controllerClass);
		Assert.notNull(resourceType);

		this.controllerClass = controllerClass;
		this.resourceType = resourceType;
	}

	/**
	 * Converts all given entities into resources.
	 * 
	 * @see #toResource(Object)
	 * @param entities
	 * must not be {@literal null}.
	 * @return
	 */
	protected List<D> toResources(Iterable<? extends T> entities) {

		Assert.notNull(entities);
		List<D> result = new ArrayList<D>();

		for (T entity : entities) {
			result.add(toResource(entity));
		}

		return result;
	}

	/**
	 * Converts all given resources into collection Items.
	 * 
	 * @see #toResource(Object)
	 * @param entities
	 * must not be {@literal null}.
	 * @return
	 * 
	 */
	protected ArrayList<Item> toItems(Iterable<? extends T> entities) {
		Assert.notNull(entities);
		ArrayList<Item> items = new ArrayList<Item>();
		for (T entity : entities) {
			D res = toResource((T) entity);
			try {
				items.add(res.asItem());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	/**
	 * Creates a new resource with a self link to the given id.
	 * 
	 * @param entity
	 * must not be {@literal null}.
	 * @param id
	 * must not be {@literal null}.
	 * @return
	 */
	protected D createResourceWithId(Object id, T entity) {
		return createResourceWithId(id, entity, new Object[0]);
	}

	protected D createResourceWithId(Object id, T entity,
			Object... parameters) {

		Assert.notNull(entity);
		Assert.notNull(id);

		D instance = instantiateResource(entity);
		// instance.add(linkTo(controllerClass,
		// parameters).slash(id).withSelfRel());
		return instance;
	}

	/**
	 * Instantiates the resource object. Default implementation will assume a
	 * no-arg constructor and use reflection but can be overridden to manually
	 * set up the object instance initially (e.g. to improve performance if this
	 * becomes an issue).
	 * 
	 * @param entity
	 * @return
	 */
	protected D instantiateResource(T entity) {
		return BeanUtils.instantiateClass(resourceType);
	}
}
