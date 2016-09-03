package bar.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bar.model.Item;
import bar.model.User;

@Singleton
public class ItemDAO {
	
	  @PersistenceContext
	    private EntityManager em;
	
	public void addItem(Item item)
	{
		em.persist(item);
		
	}
	
	
	public Item findItemByName(String itemName) {
        String txtQuery = "SELECT i FROM Item i WHERE i.itemName = :itemName";
        TypedQuery<Item> query = em.createQuery(txtQuery, Item.class);
        query.setParameter("itemName", itemName);//преди да извикаме getResultList или SingleResult (понеже е резултата е в суров вид)
        return queryItem(query);
    }

    private Item queryItem(TypedQuery<Item> query) {
        try {
            return query.getSingleResult();// може да върнем и query.getResultList().get(0); но в случая знаем че се очаква един обект и затова ползваме getSingleResult 
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public Collection<Item> getAllItems() {
        return em.createNamedQuery("getAllItems", Item.class).getResultList();
    }
    
    
	
	
	
	
	
	

}
