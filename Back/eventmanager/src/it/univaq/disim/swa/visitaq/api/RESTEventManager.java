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

import it.univaq.disim.swa.visitaq.business.EventManagerBusinessException;
import it.univaq.disim.swa.visitaq.business.EventManagerService;
import it.univaq.disim.swa.visitaq.business.impl.EventManagerServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Event;

@Path("event")
public class RESTEventManager {

	private EventManagerService service = new EventManagerServiceImpl();

	@GET
	@Path("/allEvents")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllEvents() {
		try {
			List<Event> events = service.selectEvents();
			return Response.ok(events).build();
		} catch (EventManagerBusinessException e) {
			throw new EventManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/detail/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getEventById(@PathParam("id") Long id) {
		try {
			Event event = service.selectEventDetail(id);
			
			return Response.ok(event).build();
		} catch (EventManagerBusinessException e) {
			throw new EventManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/insertEvent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertAttraction(Event event, @Context UriInfo uriInfo) {
		try {


			Event newEvent = service.insertEvent(event);

			URI userUri = uriInfo.getAbsolutePathBuilder().path(newEvent.getTitle().toString()).build();

			return Response.created(userUri).build();

		} catch (EventManagerBusinessException e) {
			throw new EventManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/deleteEvent/{id}")
	@Consumes("application/json")
	public Response deleteAttraction(@PathParam("id") Long id, Event event) {
		try {

			service.deleteEvent(id, event);

			return Response.noContent().build();
		} catch (EventManagerBusinessException e) {
			throw new EventManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/updateEvent/{id}")
	@Consumes("application/json")
	@Produces("application/json")
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
		} catch (EventManagerBusinessException e) {
			throw new EventManagerWebApplicationException("Errore interno al server");
		}
	}
}