package fr.gtm.bovoyages.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.gtm.bovoyages.dao.DestinationDAO;
import fr.gtm.bovoyages.dto.DestinationDTO;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;

@Path("/destinations")
public class DestinationService {
	
	@EJB private DestinationDAO destinationdao;
	public static final Logger LOG = Logger.getLogger("Bovoyages");
	
	@POST
	@Path("/add")
	@Consumes({"application/json;charset=utf-8"})
	public void create(Destination destination) {
		destinationdao.create(destination);
	}
	
	@POST
	@Path("/edit")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({"application/json;charset=utf-8"})
	public void update(Destination destination) {
		destinationdao.update(destination);
	}
	
	@DELETE
	@Path("/del/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void delete(@PathParam("id")String id) {
		long identifiant = Long.parseLong(id);
		destinationdao.delete(identifiant);
	}
	
	@GET
	@Path("/all")
	@Produces({"application/json;charset=utf-8"})
	public List<DestinationDTO> getAllDestinations() {
		List<DestinationDTO> destinationsdto = new ArrayList<DestinationDTO>();
		List<Destination> destinations = destinationdao.getAllDestinations();
		for(Destination d : destinations) {
			destinationsdto.add(new DestinationDTO(d));
		}
		return destinationsdto;
	}
	
	@GET
	@Path("/{id}")
	@Produces({"application/json;charset=utf-8"})
	public DestinationDTO findById(@PathParam("id")long id) {
		DestinationDTO dto = new DestinationDTO(destinationdao.findById(id));
		LOG.info(" >>>> id : "+id);
		return dto;
	}
	@GET
	@Path("/datesvoyages/{id}") 
	@Produces({"application/json;charset=utf-8"})
	public Set<DatesVoyage> getDatesVoyageByDestinationId(@PathParam("id")long id) {
		return destinationdao.getDatesVoyageByDestinationId(id);
	}
	
	@DELETE
	@Path("/datesvoyages/del/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteDatesVoyage(@PathParam("id")long id) {
		destinationdao.deleteDatesVoyage(id);
	}
}
