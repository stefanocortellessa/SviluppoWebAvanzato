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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import it.univaq.disim.swa.visitaq.business.AttractionManagerBusinessException;
import it.univaq.disim.swa.visitaq.business.AttractionManagerService;
import it.univaq.disim.swa.visitaq.business.impl.AttractionManagerServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Attraction;

@Path("attraction")
public class RESTAttractionManager {

	private AttractionManagerService service = new AttractionManagerServiceImpl();

	@GET
	@Path("/attractions")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllAttractions() {
		try {
			List<Attraction> attractions = service.selectAttractions();
			return Response.ok(attractions).build();
		} catch (AttractionManagerBusinessException e) {
			throw new AttractionManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/detail/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getEventById(@PathParam("id") Long id) {
		try {
			Attraction attraction = service.selectAttractionDetail(id);
			return Response.ok(attraction).build();
		} catch (AttractionManagerBusinessException e) {
			throw new AttractionManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/insertAttraction")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertAttraction(Attraction attraction, @Context UriInfo uriInfo) {
		try {

			Attraction newAttraction = service.insertAttraction(attraction);

			URI userUri = uriInfo.getAbsolutePathBuilder().path(newAttraction.getName().toString()).build();

			return Response.created(userUri).build();

		} catch (AttractionManagerBusinessException e) {
			throw new AttractionManagerWebApplicationException("Errore interno al server");
		}
	}

	@DELETE
	@Path("/deleteAttraction/{id}")
	@Consumes("application/json")
	public Response deleteAttraction(@PathParam("id") Long id, Attraction attraction) {
		try {

			service.deleteAttraction(id, attraction);

			return Response.noContent().build();
		} catch (AttractionManagerBusinessException e) {
			throw new AttractionManagerWebApplicationException("Errore interno al server");
		}
	}

	@PUT
	@Path("/updateAttraction/{id}")
	@Consumes("application/json")
	@Produces("application/json")
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
		} catch (AttractionManagerBusinessException e) {
			throw new AttractionManagerWebApplicationException("Errore interno al server");
		}
	}
}