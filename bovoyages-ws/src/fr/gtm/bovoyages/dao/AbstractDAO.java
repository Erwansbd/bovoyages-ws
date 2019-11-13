package fr.gtm.bovoyages.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 
 * DAO abstrait implémentaion d'un CRUD de base E : type de l'entité ID : type
 * de l'identifiant de l'entité
 */

@Stateless
public abstract class AbstractDAO<E, ID> {
	private Class<E> entityClass;

	public AbstractDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(E entity) {
		getEntityManager().persist(entity);
	}

	protected abstract EntityManager getEntityManager();

	public E findById(ID id) {

		return getEntityManager().find(entityClass, id);
	}

	public void delete(ID id) {
		E entity = getEntityManager().find(entityClass, id);
			getEntityManager().remove(entity);
	}

	public void update(E entity) {
		getEntityManager().merge(entity);
	}

}
