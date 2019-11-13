package fr.gtm.bovoyages.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;

@Singleton
public class DatesVoyageDAO extends AbstractDAO<DatesVoyage, Long> {
	
	@PersistenceContext(name="bovoyages") private EntityManager em;
	
	public DatesVoyageDAO() {
		super(DatesVoyage.class);
	}
	public List<DatesVoyage> getDatesVoyageByDestination(Destination destination) {
		List<DatesVoyage> datesvoyages = em.createNamedQuery("", DatesVoyage.class)
												.setParameter("destination", destination+"%")
												.getResultList();
		return datesvoyages;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
