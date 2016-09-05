package bar.services;

import java.net.HttpURLConnection;
import java.util.Collection;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bar.dao.OrderDAO;
import bar.model.Order;

@Stateless
@Path("order")
@DeclareRoles({ "Manager", "Waiter", "Barman" })
public class OrderManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private OrderDAO orderDAO;

	@Inject
	private UserContext context;

	@GET
	@Produces("application/json")
	@RolesAllowed({"Manager", "Barman"})
	public Collection<Order> getAllWaitingOrders() {
		if (!(context.isCallerInRole("Manager") | context.isCallerInRole("Barman"))) {
			return null;
		}
		return orderDAO.getAllWaitingOrders();
	}

	@Path("/orders")
	@GET
	@Produces("application/json")
	@RolesAllowed({"Manager", "Barman"})
	public Collection<Order> getCurrentUserOrders() {
		if (!(context.isCallerInRole("Manager") | context.isCallerInRole("Barman"))) {
			return null;
		}
		return orderDAO.getCurrentUserOrders(context.getCurrentUser());
	}
	
	@Path("/order")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"Manager", "Waiter"})
	
	public Response order(Order newOrder) {
		orderDAO.addOrder(newOrder);
		return Response.noContent().build();
	}
	
	@Path("/accept")
	@PUT
	@RolesAllowed({"Manager", "Barman"})
	public Response accept() {
		if (!(context.isCallerInRole("Manager") | context.isCallerInRole("Barman"))) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		orderDAO.setOrderAsAccepted(order, user);

	}
}
