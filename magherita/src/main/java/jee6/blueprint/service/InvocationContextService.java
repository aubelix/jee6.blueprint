package jee6.blueprint.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

/**
 * This service is used to hold information during one transaction.
 * 
 * @author max_kuffs
 * 
 */
@Stateless
public class InvocationContextService {

	@Resource
	private TransactionSynchronizationRegistry registry;

	public void put(String key, Object object) {
		registry.putResource(key, object);
	}

	public Object get(String key) {
		return registry.getResource(key);
	}

	public <E> E get(String key, Class<E> clazz) {
		Object object = get(key);
		if(object == null) {
			return null;
		}
		
		return clazz.cast(object);
	}

}
