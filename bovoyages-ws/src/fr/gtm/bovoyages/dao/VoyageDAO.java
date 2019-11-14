package fr.gtm.bovoyages.dao;
//
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.gtm.bovoyages.entities.Voyage;

@Singleton
public class VoyageDAO extends AbstractDAO<Voyage, Long> {
	@PersistenceContext(name="bovoyages") private EntityManager em;

	public VoyageDAO() {
		super(Voyage.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
}
