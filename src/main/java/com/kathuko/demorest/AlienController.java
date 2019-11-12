package com.kathuko.demorest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("alien")
public class AlienController {

//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public Alien getAlien() {
//		System.out.println("Alien Get");
//		Alien a1 = new Alien();
//		a1.setName("Vivek");
//		a1.setPoints(10);
//
//		return a1;
//	}
	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAliens() {

		return repo.getAliens();
	}

	@GET
	@Path("alienId/{id}")
	// Content Negotiation Server to Client ------- OUTPUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}

	@POST // For Creation
	@Path("alienCreate")
	// Client to Server ----- INPUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		repo.create(a1);

		return a1;
	}

	@PUT // For Updation, but it can create as well
	@Path("alienUpdate")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien updateAlien(Alien alien) {
		// If alien is not present create
		if (repo.getAlien(alien.getId()) == null) {
			repo.create(alien);
		} else {
			// Else create
			repo.update(alien);
		}

		return alien;
	}

	@DELETE
	@Path("delete/{id}")
	public Alien killAlien(@PathParam("id") int id) {
		Alien a = repo.getAlien(id);
		if (a != null) {
			repo.delete(id);
		}
		return a;
	}
}
