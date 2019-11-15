package fr.gtm.bovoyages.services.rest;
//
import java.util.ArrayList;
import java.util.HashSet;
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
import fr.gtm.bovoyages.entities.Voyage;

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
	@Path("/all/valid")
	@Produces({"application/json;charset=utf-8"}) 
	public List<DestinationDTO> getValidDestinations() {
		List<DestinationDTO> destinationsdto = new ArrayList<DestinationDTO>();
		List<Destination> destinations = destinationdao.getValidDestinations();
		for(Destination d : destinations) {
			destinationsdto.add(new DestinationDTO(d));
		}
		return destinationsdto;
	}
	
	@GET
	@Path("/{id}/dates/valid")
	@Produces({"application/json;charset=utf-8"})
	public Set<DatesVoyage> getValidReducedDatesVoyages(@PathParam("id")long id) {
		return destinationdao.getValidDatesVoyagesByDestinationId(id);
	}
	
	@GET
	@Path("/reduced")
	@Produces({"application/json;charset=utf-8"})
	public Set<DestinationDTO> getDestinationByReducedDatesVoyage() {
		Set<DestinationDTO> dtos = new HashSet<DestinationDTO>();
		List<Destination> destinations = destinationdao.getValidDestinations();
		for(Destination d : destinations) {
			Set<DatesVoyage> datesVoyages = destinationdao.getDatesVoyageByDestinationId(d.getId());
					for(DatesVoyage date : datesVoyages)
						if(date.getPromotion() == 1) {
							dtos.add(new DestinationDTO(d));
						}
		}
		return dtos;
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
	
	@POST
	@Path("/voyage/add")
	@Consumes({"application/json;charset=utf-8"})
	public void orderVoyage(Voyage voyage) {
		destinationdao.orderVoyage(voyage);
	}
	
	@GET
	@Path("/voyage/all")
	@Produces({"application/json;charset=utf-8"})
	public List<Voyage> getVoyage() {
		return destinationdao.getVoyage();
	}
	
	@POST
	@Path("/voyage/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVoyage(Voyage voyage) {
		destinationdao.updateVoyage(voyage);
	}
	
	@GET
	@Path("/all/regions")
	@Produces({"application/json;charset=utf-8"})
	public String[] getAllRegions() {
		return destinationdao.getAllRegions();
	}
	
	@DELETE
	@Path("/voyage/del/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteVoyage(@PathParam("id") long id) {
		destinationdao.deleteVoyage(id);
	}
	
	@GET
	@Path("/voyage/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Voyage getVoyageById(@PathParam("id") long id) {
	   return destinationdao.getVoyageById(id);
	}
}
