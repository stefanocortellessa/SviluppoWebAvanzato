package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class VisitaqWebApplicationException extends WebApplicationException {

	public VisitaqWebApplicationException() {
		super(Response.serverError().build());
	}
	public VisitaqWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
