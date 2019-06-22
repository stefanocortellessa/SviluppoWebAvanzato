package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EventResourceWebApplicationException extends WebApplicationException {

	public EventResourceWebApplicationException() {
		super(Response.serverError().build());
	}
	public EventResourceWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
