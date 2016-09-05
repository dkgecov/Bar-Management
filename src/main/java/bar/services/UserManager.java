package bar.services;

import java.net.HttpURLConnection;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bar.dao.UserDAO;
import bar.model.User;

@Stateless
@Path("user")
@DeclareRoles({ "Manager", "Waiter", "Barman" })
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserContext context;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("Manager")
	public Response registerUser(User newUser) {
		if (!context.isCallerInRole("Manager")) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}

		boolean userNameExists = userDAO.userNameExists(newUser.getUserName());
		boolean emailExists = userDAO.emailExists(newUser.getEmail());

		if (userNameExists) {
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build();
		}
		if (emailExists) {
			return Response.status(HttpURLConnection.HTTP_CONFLICT).build();
		}

		userDAO.addUser(newUser);
		context.setCurrentUser(newUser);
		return RESPONSE_OK;
	}

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response loginUser(User user) {
		boolean isUserValid = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		context.setCurrentUser(user);
		return RESPONSE_OK;
	}

	@Path("authenticated")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response isAuthenticated() {
		if (context.getCurrentUser() == null) {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
		}
		return RESPONSE_OK;
	}

	// Ползва ли се във jquery?
	@Path("current")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@PermitAll
	public String getUser() {
		if (context.getCurrentUser() == null) {
			return null;
		}
		return context.getCurrentUser().getUserName();
	}

	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@PermitAll
	public void logoutUser() {
		System.out.println("user logging out");
		context.setCurrentUser(null);
	}

}
