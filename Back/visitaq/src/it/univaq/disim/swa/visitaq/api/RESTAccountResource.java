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

import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.business.impl.AccountResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

@Path("user")
public class RESTAccountResource {

	private AccountResourceService accountService = new AccountResourceServiceImpl();
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertUser(User user) {
		try {
			Boolean responseMessage = accountService.insertUser(user);
			
			return Response.ok(responseMessage).build();
			
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response checkSession(@PathParam("token") String token) {
		try {
			Boolean responseMessage = accountService.checkSession(token);
			
			return Response.ok(responseMessage).build();
			
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response loginUser(User user) {
		try {
			Session userToken = accountService.loginUser(user);
			
			if(userToken != null) {
				return Response.ok(userToken.getToken()).status(201).build();
	        } else {
	            return Response.status(403).build();
	        }
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/logout/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response logout(@PathParam("token") String token) {
		try {
			accountService.logoutUser(token);
			return Response.noContent().build();
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/{token}/delete/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteUser(@PathParam("id") Long id, @PathParam("token") String token) {
		try {
			accountService.deleteUser(id);
			return Response.noContent().build();
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/{token}/update/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateUser(User user, @PathParam("id") Long id, @PathParam("token") String token) {
		
			if (user == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (id == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			try {
				if(accountService.checkSession(token)) {
					accountService.updateUser(user, id);
					return Response.noContent().build();
				} else {
					return Response.status(401).build();
				}
			} catch (VisitaqBusinessException e) {
				throw new VisitaqWebApplicationException("Errore interno al server");
			}
		
	}
}