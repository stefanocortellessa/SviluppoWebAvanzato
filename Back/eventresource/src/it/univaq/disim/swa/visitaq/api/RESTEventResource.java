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

import it.univaq.disim.swa.visitaq.business.EventResourceBusinessException;
import it.univaq.disim.swa.visitaq.business.EventResourceService;
import it.univaq.disim.swa.visitaq.business.impl.EventResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Event;

@Path("events")
public class RESTEventResource {

	private EventResourceService service = new EventResourceServiceImpl();

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllEvents() {
		try {
			List<Event> events = service.selectEvents();
			return Response.ok(events).build();
		} catch (EventResourceBusinessException e) {
			throw new EventResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEventById(@PathParam("id") Long id) {
		try {
			Event event = service.selectEventDetail(id);
			
			return Response.ok(event).build();
		} catch (EventResourceBusinessException e) {
			throw new EventResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertAttraction(Event event, @Context UriInfo uriInfo) {
		try {


			Event newEvent = service.insertEvent(event);

			URI userUri = uriInfo.getAbsolutePathBuilder().path(newEvent.getTitle().toString()).build();

			return Response.created(userUri).build();

		} catch (EventResourceBusinessException e) {
			throw new EventResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteAttraction(@PathParam("id") Long id, Event event) {
		try {

			service.deleteEvent(id, event);

			return Response.noContent().build();
		} catch (EventResourceBusinessException e) {
			throw new EventResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateAttraction(@PathParam("id") Long id, @Context UriInfo uriInfo, Event event) {

		if (event == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			Event newEvent = service.updateEvent(event, id);
			
			URI userUri = uriInfo.getAbsolutePathBuilder()
	                .path(newEvent.getTitle().toString())
	                .build();
			
			return Response.created(userUri).build();
		} catch (EventResourceBusinessException e) {
			throw new EventResourceWebApplicationException("Errore interno al server");
		}
	}
}