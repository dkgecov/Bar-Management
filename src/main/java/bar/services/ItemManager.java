package bar.services;

import java.net.HttpURLConnection;
import java.util.Collection;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bar.dao.ItemDAO;
import bar.model.Item;

@Stateless
@Path("item")
@DeclareRoles({ "Manager", "Waiter", "Barman" })
public class ItemManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private ItemDAO itemDAO;

	@Inject
	private UserContext context;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response addNewItem(Item newItem) {
		itemDAO.addItem(newItem);
		return RESPONSE_OK;
	}

	@Path("/items")
	@GET
	@Produces("application/json")
	@PermitAll
	public Collection<Item> getAllItems() {

		return itemDAO.getAllItems();
	}

}
