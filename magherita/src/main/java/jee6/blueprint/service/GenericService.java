package jee6.blueprint.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response.Status;

import jee6.blueprint.bundle.BundleKey;
import jee6.blueprint.exception.AppException;

public abstract class GenericService {
	@PersistenceContext(unitName = "magherita")
	protected EntityManager em;

	public static final String undefined = "undefined";

	public <T> T findEntity(Class<T> clazz, Object id) {
		if (id == null) {
			return null;
		}

		return em.find(clazz, id);
	}

	public <T> T getEntity(Class<T> clazz, Object id, BundleKey bundleKey) {
		if (undefined.equals(id)) {
			throw new AppException(bundleKey, Status.NOT_FOUND,
					id);
		}

		T entity = findEntity(clazz, id);
		if (entity == null) {
			throw new AppException(bundleKey, Status.NOT_FOUND,
					id);
		}

		return entity;
	}
	
	public <T> T getEntity(Class<T> clazz, Object id) {
		return getEntity(clazz, id, BundleKey.NOT_FOUND);
	}

	public <T> void remove(Class<T> clazz, Object id) {
		T entity = getEntity(clazz, id);
		em.remove(entity);
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public <E> E getSingleNullableResult(TypedQuery<E> query) {
		List<E> res = query.getResultList();

		if (res.isEmpty()) {
			return null;
		}

		return res.get(0);
	}

}
