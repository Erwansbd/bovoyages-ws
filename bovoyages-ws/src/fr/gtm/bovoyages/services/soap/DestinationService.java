package fr.gtm.bovoyages.services.soap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.gtm.bovoyages.dao.DestinationDAO;
import fr.gtm.bovoyages.dao.VoyageDAO;
import fr.gtm.bovoyages.dto.DestinationDTO;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;

@Stateless
@WebService
public class DestinationService implements DestinationServiceRemote, DestinationServiceLocal {
	@EJB
	private DestinationDAO dao;
	@EJB
	private VoyageDAO voyagedao;

	@Override
	public Set<DestinationDTO> getDestinationByReducedDatesVoyage() {
		Set<DestinationDTO> dtos = new HashSet<DestinationDTO>();
		List<Destination> destinations = dao.getValidDestinations();
		for (Destination d : destinations) {
			Set<DatesVoyage> datesVoyages = dao.getDatesVoyageByDestinationId(d.getId());
			for (DatesVoyage date : datesVoyages)
				if (date.getPromotion() == 1) {
					dtos.add(new DestinationDTO(d));
				}
		}
		return dtos;
	}

	@Override
	public void orderVoyage(Voyage voyage) {
		dao.orderVoyage(voyage);
	}
}
