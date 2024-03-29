package it.univaq.disim.swa.visitaq.api;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.business.impl.AccountResourceServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Attraction;
import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

@Path("users")
public class RESTAccountResource {

	private AccountResourceService accountService = new AccountResourceServiceImpl();
	
	@GET
	@Path("/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllUsers(@PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				List<User> users = accountService.selectAllUsers();
				
				return Response.ok(users).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@GET
	@Path("/{token}/email/{email}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllUsersByEmail(@PathParam("token") String token, @PathParam("email") String email) {
		try {
			if(accountService.checkSession(token)) {
				User user = accountService.selectUserByEmail(email);
				
				if (user.getEmail() != null) {
					return Response.ok(user).build();
				} else {
					return Response.noContent().build();
				}
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insertUser(User user, @Context UriInfo uriInfo) {
		try {
			Boolean responseMessage = accountService.insertUser(user);
			
			if(responseMessage) {
				return Response.ok(responseMessage).status(Status.CREATED).build();
			} else {
				return Response.status(Status.FORBIDDEN).build();
			}	
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/auth/{token}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response checkSession(User user, @PathParam("token") String token) {
		try {
			Boolean responseMessage = accountService.checkSession(token);
			if(responseMessage) {
				return Response.ok(responseMessage).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
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
			Boolean response =accountService.logoutUser(token);
			if(response) {
				return Response.noContent().build();
			} else {
				return Response.ok().build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response loginUser(@Context UriInfo context, User user) {
		try {
			Session userToken = accountService.loginUser(user);
			
			if(userToken.getToken() != null) {
				return Response.ok(userToken.getToken()).status(201).build();
	        } else {
	            return Response.status(Status.FORBIDDEN).build();
	        }
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/{token}/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response deleteUser(@PathParam("id") Long id, @PathParam("token") String token) {
		try {
			if(accountService.checkSession(token)) {
				Boolean response = accountService.deleteUser(id);
				if(response) {
					return Response.noContent().build();
				} else {
					return Response.ok().build();
				}
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/{token}/{id}")
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
				User response = accountService.updateUser(user, id);
				
				if(response.getEmail() != null) {
					return Response.noContent().build();
				} else {
					return Response.ok().build();
				}
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (VisitaqBusinessException e) {
			throw new VisitaqWebApplicationException("Errore interno al server");
		}
	}
	
	@Path("{token}/attractions") 
	public RESTAttractionResource getAttractions() {
        return new RESTAttractionResource();
    }
	
	@Path("{token}/events") 
	public RESTEventResource getEvents() {
        return new RESTEventResource();
    }
}