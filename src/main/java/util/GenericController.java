package util;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import util.interfaces.BaseService;

public abstract class GenericController<T, D> {
	
	public final BaseService<T, D> service;

	public GenericController(BaseService<T, D> service) {
		this.service = service;
	}

	/*
	 * @GetALL Create entity
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public D createEntity(@RequestBody D collection) throws Exception {
		System.out.println(collection);
		service.createEntity(collection);
		return getAllEntities();
	}

	/*
	 * @deleteEntity Update entity of an Object
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public D deleteEntity(@PathVariable("id") T id) throws Exception {
		service.deleteEntity(id);
		return getAllEntities();
	}

	/*
	 * @GetALL Get All entities of an Object
	 * 
	 */
	@RequestMapping(method = GET, path = "")
	public D getAllEntities() throws Exception {
		return service.getAllEntities();
	}

	/*
	 * @GetEntity Get One entity
	 * 
	 */
	@RequestMapping(method = GET, path = "/{id}")
	public D getOneEntity(@PathVariable T id) throws Exception {
		return service.getSingleEntity(id);
	}

	/*
	 * @executeCommand execute Command
	 * 
	 */
	@RequestMapping(method = RequestMethod.PATCH, path = { "/{id}/*", "*","/{id}" })
	public D patchEntity(@PathVariable T id,
			@RequestHeader("domain-model") String command,
			@RequestBody String json) throws Exception {
		for (Method method : service.getClass().getDeclaredMethods())
			if (method.getName().equals(command)) {
				Class<?>[] params = method.getParameterTypes();
				if (params.length == 1) {
					ObjectMapper mapper = new ObjectMapper();
					System.out.print("JSON " + json + "Params " + params[0]);
					method.invoke(service, mapper.readValue(json, params[0]));
				} else if (params.length == 0) {
					try {
						method.invoke(service);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new Exception("Command not found");
				}
			}
		return getOneEntity(id);
	}

	/*
	 * @UpdateEntity Update entity of an Object
	 * 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = { "/{id}/*", "*","/{id}" })
	public D updateEntity(@PathVariable T id, @RequestBody D collection,
			@RequestHeader("domain-model") String command) throws Exception {
		if(command.equals("edit")){
			service.updateEntity(id, collection);
		}
		excecuteCommand(id, collection, command);
		
		return getOneEntity(id);
	}

	private void excecuteCommand(T id, D collection, String command)
			throws IllegalAccessException, InvocationTargetException,
			Exception {
		for (Method method : service.getClass().getDeclaredMethods()){
			if (method.getName().equals(command)) {
				Class<?>[] params = method.getParameterTypes();
				if (params.length == 1) {
					method.invoke(service,id);
				} else if (params.length == 0) {
					method.invoke(service);
				} else if(params.length > 1){
					System.out.print(
							"JSON " + collection + "Params " + params[0]);
					method.invoke(service,id,collection.toString());
				} else {
					throw new Exception("Command not found");
				}
			}
		}
	}
}
