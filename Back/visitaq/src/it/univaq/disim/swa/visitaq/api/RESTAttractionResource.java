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
import javax.ws.rs.core.UriBuilderException;

import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.business.AttractionResourceService;
import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.business.impl.AccountResourceServiceImpl;
import it.univaq.disim.swa.visitaq.business.impl.AttractionResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Attraction;

@Path("attractions")
public class RESTAttractionResource {

	private AttractionResourceService attractionService = new AttractionResourceServiceImpl();
	private AccountResourceService accountService = new AccountResourceServiceImpl();
	
	@GET
	@Path("/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllAttractions(@PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				List<Attraction> attractions = attractionService.selectAttractions();
				return Response.ok(attractions).build();
			} else {
				return Response.status(401).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/{id}/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventById(@PathParam("id") Long id, @PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				Attraction attraction = attractionService.selectAttractionDetail(id);
				return Response.ok(attraction).build();
			}else {
				return Response.status(401).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/add/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertAttraction(@PathParam("token") String token, Attraction attraction, @Context UriInfo uriInfo) {
		try {
			if(accountService.checkSession(token)) {
				Attraction newAttraction = attractionService.insertAttraction(attraction);
				URI userUri = uriInfo.getAbsolutePathBuilder().path(newAttraction.getName().toString()).build();

				return Response.created(userUri).build();
			} else {
				return Response.status(401).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}

	@DELETE
	@Path("/delete/{id}/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteAttraction(@PathParam("id") Long id, @PathParam("token") String token, Attraction attraction) {
		try {
			if(accountService.checkSession(token)) {
				attractionService.deleteAttraction(id, attraction);

				return Response.noContent().build();
			} else {
				return Response.status(401).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}

	@PUT
	@Path("/update/{id}/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateAttraction(@PathParam("id") Long id, @PathParam("token") String token, @Context UriInfo uriInfo, Attraction attraction) throws IllegalArgumentException, UriBuilderException {
		
		
			if (attraction == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (id == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			try {	
				if(accountService.checkSession(token)) {
					Attraction newAttraction = attractionService.updateAttraction(attraction, id);
					
					URI userUri = uriInfo.getAbsolutePathBuilder()
			                .path(newAttraction.getName().toString())
			                .build();
					
					return Response.created(userUri).build();
				} else {
					return Response.status(401).build();
				}
			} catch (VisitaqBusinessException e) {
				throw new VisitaqWebApplicationException("Errore interno al server");
			}
		
	}
}