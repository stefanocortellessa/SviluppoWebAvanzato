package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AttractionManagerWebApplicationException extends WebApplicationException {

	public AttractionManagerWebApplicationException() {
		super(Response.serverError().build());
	}
	public AttractionManagerWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
