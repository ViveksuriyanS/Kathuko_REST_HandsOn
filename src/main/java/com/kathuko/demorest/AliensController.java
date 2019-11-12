package com.kathuko.demorest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AliensController {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Alien> getAliens() {
		Alien a1 = new Alien();
		a1.setId(101);
		a1.setName("Vivek");
		a1.setPoints(5);
		Alien a2 = new Alien();
		a2.setId(102);
		a2.setName("Suriyan");
		a2.setPoints(4);
		List<Alien> alienList = Arrays.asList(a1, a2);
		
		return alienList;
	}
}
