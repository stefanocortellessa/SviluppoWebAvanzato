package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AttractionResourceWebApplicationException extends WebApplicationException {

	public AttractionResourceWebApplicationException() {
		super(Response.serverError().build());
	}
	public AttractionResourceWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
