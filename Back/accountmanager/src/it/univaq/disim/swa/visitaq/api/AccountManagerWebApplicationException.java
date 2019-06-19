package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AccountManagerWebApplicationException extends WebApplicationException {

	public AccountManagerWebApplicationException() {
		super(Response.serverError().build());
	}

	public AccountManagerWebApplicationException(String message) {
		super(Response.serverError()
				      .entity(message)
				      .type("text/plain")
				      .build());
	}
}
