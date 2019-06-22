package it.univaq.disim.swa.visitaq.api;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import it.univaq.disim.swa.visitaq.business.AttractionResourceBusinessException;
import it.univaq.disim.swa.visitaq.business.AttractionResourceService;
import it.univaq.disim.swa.visitaq.business.impl.AttractionResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Attraction;

@Path("attractions")
public class RESTAttractionResource {

	private AttractionResourceService service = new AttractionResourceServiceImpl();

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllAttractions() {
		try {
			List<Attraction> attractions = service.selectAttractions();
			return Response.ok(attractions).build();
		} catch (AttractionResourceBusinessException e) {
			throw new AttractionResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventById(@PathParam("id") Long id) {
		try {
			Attraction attraction = service.selectAttractionDetail(id);
			return Response.ok(attraction).build();
		} catch (AttractionResourceBusinessException e) {
			throw new AttractionResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertAttraction(Attraction attraction, @Context UriInfo uriInfo) {
		try {

			Attraction newAttraction = service.insertAttraction(attraction);

			URI userUri = uriInfo.getAbsolutePathBuilder().path(newAttraction.getName().toString()).build();

			return Response.created(userUri).build();

		} catch (AttractionResourceBusinessException e) {
			throw new AttractionResourceWebApplicationException("Errore interno al server");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteAttraction(@PathParam("id") Long id, Attraction attraction) {
		try {

			service.deleteAttraction(id, attraction);

			return Response.noContent().build();
		} catch (AttractionResourceBusinessException e) {
			throw new AttractionResourceWebApplicationException("Errore interno al server");
		}
	}

	@PUT
	@Path("/update/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateAttraction(@PathParam("id") Long id, @Context UriInfo uriInfo, Attraction attraction) {

		if (attraction == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			Attraction newAttraction = service.updateAttraction(attraction, id);
			
			URI userUri = uriInfo.getAbsolutePathBuilder()
	                .path(newAttraction.getName().toString())
	                .build();
			
			return Response.created(userUri).build();
		} catch (AttractionResourceBusinessException e) {
			throw new AttractionResourceWebApplicationException("Errore interno al server");
		}
	}
}