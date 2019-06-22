package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AccountResourceWebApplicationException extends WebApplicationException {

	public AccountResourceWebApplicationException() {
		super(Response.serverError().build());
	}

	public AccountResourceWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
