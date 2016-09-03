package bar.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bar.dao.ItemDAO;
import bar.model.Item;
import bar.model.User;

@Stateless
public class ItemManager {

	 @Inject
	    private ItemDAO itemDAO;
	
	
	
	 @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void addNewItem(Item newItem) {
	        itemDAO.addItem(newItem);
	        
	    }
	 
	 
	 @GET
	    @Produces("application/json")
	    public Collection<Item> getAllItems() {
	        return itemDAO.getAllItems();
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}
