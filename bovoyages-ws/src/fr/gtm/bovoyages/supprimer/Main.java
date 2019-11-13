package fr.gtm.bovoyages.supprimer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.services.DestinationService;

public class Main {

	public static void main(String[] args) {
		String domaine = "http://localhost:9080/bovoyages-ws/rest/destinations";
		String uri = domaine + "/add";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		
		DestinationService service = new DestinationService();
		Destination d = new Destination("region", "description");
		Destination d1 = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(d, MediaType.APPLICATION_JSON), Destination.class);
		service.create(d);

	}

}
