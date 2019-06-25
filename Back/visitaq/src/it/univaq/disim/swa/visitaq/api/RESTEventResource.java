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

import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.business.EventResourceService;
import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.business.impl.AccountResourceServiceImpl;
import it.univaq.disim.swa.visitaq.business.impl.EventResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Attraction;
import it.univaq.disim.swa.visitaq.domain.Event;

public class RESTEventResource {

	private EventResourceService eventService = new EventResourceServiceImpl();
	private AccountResourceService accountService = new AccountResourceServiceImpl();
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllEvents(@PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				List<Event> events = eventService.selectEvents();
				
				return Response.ok(events).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException ex) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventById(@PathParam("id") Long id, @PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				Event event = eventService.selectEventDetail(id);
				
				return Response.ok(event).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/idCreator/{userId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventsByUserId(
			@PathParam("token") String token, 
			@PathParam("userId") Long userID) {
		
		try {
			if(accountService.checkSession(token)) {
				List<Event> events = eventService.selectEventsByUser(userID);
				
				return Response.ok(events).build();
			}else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/idCategory/{categoryId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventsByCategory(
			@PathParam("token") String token, 
			@PathParam("categoryId") Long categoryId) {
		
		try {
			if(accountService.checkSession(token)) {
				List<Event> events = eventService.selectEventsByCategory(categoryId);
				
				return Response.ok(events).build();
			}else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertEvent(@PathParam("token") String token, Event event, @Context UriInfo uriInfo) {
		try {
			if(accountService.checkSession(token)) {
				Event newEvent = eventService.insertEvent(event);
				
				if(newEvent != null) {
					
					URI Uri = uriInfo.getAbsolutePathBuilder().path(newEvent.getTitle().toString()).build();

					return Response.created(Uri).build();
				} else {
					return Response.ok().build();
				}
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteEvent(@PathParam("id") Long id, @PathParam("token") String token, Event event) {
		try {
			if(accountService.checkSession(token)) {
				
				eventService.deleteEvent(id, event);
				return Response.noContent().build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateEvent(@PathParam("id") Long id, @PathParam("token") String token, @Context UriInfo uriInfo, Event event) {

		if (event == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			if(accountService.checkSession(token)) {
				eventService.updateEvent(event, id);
				return Response.noContent().build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
}