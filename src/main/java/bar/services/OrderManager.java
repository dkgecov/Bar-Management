package bar.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bar.dao.OrderDAO;
import bar.model.Order;

@Stateless
@Path("order")
public class OrderManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private OrderDAO orderDAO;

	@Inject
	private UserContext userContext;

	@GET
	@Path("/getAll")
	@Produces("application/json")
	public Collection<Order> getAllWaitingOrders() {
		return orderDAO.getAllWaitingOrders();
	}

	@Path("/userOrders")
	@GET
	@Produces("application/json")
	public Collection<Order> getCurrentUserOrders() {
		return orderDAO.getCurrentUserOrders(userContext.getCurrentUser());
	}
	
	@Path("/order")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response order(Order newOrder) {
		orderDAO.addOrder(newOrder);
		return Response.noContent().build();
	}
}
