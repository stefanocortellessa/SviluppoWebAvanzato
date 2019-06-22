package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.univaq.disim.swa.visitaq.business.AccountResourceBusinessException;
import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.business.impl.AccountResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

@Path("user")
public class RESTAccountResource {

	private AccountResourceService service = new AccountResourceServiceImpl();
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertUser(User user) {
		try {
			Boolean responseMessage = service.insertUser(user);
			
			return Response.ok(responseMessage).build();
			
		} catch (AccountResourceBusinessException e) {
			throw new AccountResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response loginUser(User user) {
		try {
			Session userToken = service.loginUser(user);
			
			if(userToken != null) {
				return Response.ok(userToken.getToken()).status(201).build();
	        } else {
	            return Response.status(403).build();
	        }
		} catch (AccountResourceBusinessException e) {
			throw new AccountResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/logout/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response logout(@PathParam("token") String token) {
		try {
			service.logoutUser(token);
			return Response.noContent().build();
		} catch (AccountResourceBusinessException e) {
			throw new AccountResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteUser(@PathParam("id") Long id) {
		try {
			service.deleteUser(id);
			return Response.noContent().build();
		} catch (AccountResourceBusinessException e) {
			throw new AccountResourceWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateUser(User user, @PathParam("id") Long id) {
		if (user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			service.updateUser(user, id);
			return Response.noContent().build();
		} catch (AccountResourceBusinessException e) {
			throw new AccountResourceWebApplicationException("Errore interno al server");
		}
	}
}