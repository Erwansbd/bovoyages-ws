package fr.gtm.bovoyages.dao;

//
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import fr.gtm.bovoyages.dto.DestinationDTO;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;

@Singleton
public class DestinationDAO extends AbstractDAO<Destination, Long> {

	@PersistenceContext(name = "bovoyages")
	private EntityManager em;
	public static final Logger LOG = Logger.getLogger("Bovoyages");

	public DestinationDAO() {
		super(Destination.class);
	}

	public List<Destination> getAllDestinations() {
		List<Destination> destinations = em.createNamedQuery("Destination.getAllDestinations", Destination.class)
				.getResultList();
		return destinations;
	}

	public List<Destination> getValidDestinations() {
		List<Destination> destinations = em.createNamedQuery("Destination.getAllValidDestinations", Destination.class)
				.getResultList();
		return destinations;
	}

	public Set<DatesVoyage> getDatesVoyageByDestinationId(long id) {
		Destination destination = em.find(Destination.class, id);
		Set<DatesVoyage> datesVoyage = new HashSet<DatesVoyage>();
		for (DatesVoyage d : destination.getDatesVoyage()) {
			datesVoyage.add(d);
		}
		return datesVoyage;
	}

	public Set<DatesVoyage> getValidDatesVoyagesByDestinationId(long id) {
		Destination destination = em.find(Destination.class, id);
		Set<DatesVoyage> datesVoyage = new HashSet<DatesVoyage>();
		for (DatesVoyage d : destination.getDatesVoyage()) {
			if (d.getDeleted() == 0) {
				datesVoyage.add(d);
			}
		}
		return datesVoyage;
	}

	public String[] getAllRegions() {
		List<Destination> destinations = em.createNamedQuery("Destination.getAllDestinations", Destination.class)
				.getResultList();
		String[] regions = new String[destinations.size()];
		int i = 0;
		for (Destination d : destinations) {
			regions[i] = d.getRegion();
			i++;
		}
		return regions;
	}

	public void orderVoyage(Voyage voyage) {
		if (voyage.getDatesVoyage().getNbrePlaces() > voyage.getVoyageurs().size() && voyage.getVoyageurs().size() >= 1
				&& voyage.getVoyageurs().size() <= 10) {
			LOG.info(" >>>>>" + voyage.getDatesVoyage().getNbrePlaces());
			voyage.getDatesVoyage()
					.setNbrePlaces(voyage.getDatesVoyage().getNbrePlaces() - voyage.getVoyageurs().size());
			DatesVoyage dv = voyage.getDatesVoyage();
			voyage.setDatesVoyage(dv);
			LOG.info(">>>>>" + voyage.getVoyageurs().size());
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
			em.persist(voyage);
			updateDV(dv);
			LOG.info(" >>>>" + voyage.getDatesVoyage().getNbrePlaces());
		}
	}

	public void updateDV(DatesVoyage datesVoyage) {
		DatesVoyage dv = em.find(DatesVoyage.class, datesVoyage.getId());
		dv.setDateAller(datesVoyage.getDateAller());
		dv.setDateRetour(datesVoyage.getDateRetour());
		dv.setDeleted(datesVoyage.getDeleted());
		dv.setNbrePlaces(datesVoyage.getNbrePlaces());
		em.merge(dv);
	}

	public List<Voyage> getVoyage() {
		List<Voyage> voyages = em.createNamedQuery("Voyage.getAllVoyages", Voyage.class).getResultList();
		return voyages;
	}

	public void updateVoyage(Voyage voyage) {
		Voyage voyageSaved = em.find(Voyage.class, voyage.getId());
		voyageSaved.setVoyageurs(voyage.getVoyageurs());
		em.merge(voyageSaved);
	}

	public Voyage getVoyageById(long id) {
		Voyage voyage = em.find(Voyage.class, id);
		return voyage;
	}

	public void deleteDatesVoyage(long id) {
		DatesVoyage datesVoyage = em.find(DatesVoyage.class, id);
		em.remove(datesVoyage);
	}

	public void deleteVoyage(long id) {
		Voyage voyage = em.find(Voyage.class, id);
		em.remove(voyage);
	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}
}
