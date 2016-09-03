package bar.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import bar.dao.ItemDAO;
import bar.model.Item;
import bar.model.User;

public class ItemManager {

	 @Inject
	    private ItemDAO itemDAO;
	
	
	
	 @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void addNewItem(Item newItem) {
	        itemDAO.addItem(newItem);
	        
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}
