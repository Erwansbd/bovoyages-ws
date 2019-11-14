package fr.gtm.bovoyages.services.soap;

import java.util.List;
import java.util.Set;

import fr.gtm.bovoyages.dto.DestinationDTO;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;

public interface DestinationServiceInterface {
	
	public Set<DestinationDTO> getDestinationByReducedDatesVoyage();
	void orderVoyage(Voyage voyage);

}
