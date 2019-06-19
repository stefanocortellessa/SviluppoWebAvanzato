package it.univaq.disim.swa.visitaq.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.univaq.disim.swa.visitaq.business.AccountManagerBusinessException;
import it.univaq.disim.swa.visitaq.business.AccountManagerService;
import it.univaq.disim.swa.visitaq.business.impl.AccountManagerServiceImpl;
import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

@Path("account")
public class RESTAccountManager {

	private AccountManagerService service = new AccountManagerServiceImpl();
	
	@POST
	@Path("/insertUser")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertUser(User user) {
		try {
			Boolean responseMessage = service.insertUser(user);
			
			return Response.ok(responseMessage).build();
			
		} catch (AccountManagerBusinessException e) {
			throw new AccountManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response loginUser(User user) {
		try {
			Session userToken = service.loginUser(user);
			
			
			return Response.ok(userToken.getToken()).build();
			
		} catch (AccountManagerBusinessException e) {
			throw new AccountManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/logout/{token}")
	@Consumes("application/json")
	public Response logout(@PathParam("token") String token) {
		try {
			service.logoutUser(token);
			return Response.noContent().build();
		} catch (AccountManagerBusinessException e) {
			throw new AccountManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@DELETE
	@Path("/deleteUser/{id}")
	@Consumes("application/json")
	public Response deleteUser(@PathParam("id") Long id) {
		try {
			service.deleteUser(id);
			return Response.noContent().build();
		} catch (AccountManagerBusinessException e) {
			throw new AccountManagerWebApplicationException("Errore interno al server");
		}
	}
	
	@PUT
	@Path("/updateUser/{id}")
	@Consumes("application/json")
	@Produces("application/json")
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
		} catch (AccountManagerBusinessException e) {
			throw new AccountManagerWebApplicationException("Errore interno al server");
		}
	}
}