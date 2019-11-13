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
public class DestinationDAO extends AbstractDAO<Destination, Long> {
	
	@PersistenceContext(name="bovoyages") private EntityManager em;
	
	public DestinationDAO() {
		super(Destination.class);
	}
	
	public List<Destination> getAllDestinations() {
		List<Destination> destinations = em.createNamedQuery("Destination.getAllDestinations", Destination.class).getResultList();
		return destinations;
	}
	
	public Set<DatesVoyage> getDatesVoyageByDestinationId(long id){
		Destination destination = em.find(Destination.class, id);
		Set<DatesVoyage> datesVoyage = new HashSet<DatesVoyage>();
		for (DatesVoyage d : destination.getDatesVoyage()){
			datesVoyage.add(d);
			}
		return datesVoyage;
	}
	
	public void deleteDatesVoyage(long id) {
		DatesVoyage datesVoyage = em.find(DatesVoyage.class, id);
		em.remove(datesVoyage);
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
}
