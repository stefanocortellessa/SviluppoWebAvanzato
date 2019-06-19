package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EventManagerWebApplicationException extends WebApplicationException {

	public EventManagerWebApplicationException() {
		super(Response.serverError().build());
	}
	public EventManagerWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
