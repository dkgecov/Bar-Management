package bar.dao;

import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton

public class ProfitDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	
	
	public double estimateDailyProfit(){
		
		Date curentdate=new Date();
		int day=new Date().getDay();
		//SELECT SUM(Quantity) AS TotalItemsOrdered FROM OrderDetails
		//where Quantity<30
		String txtQuery = "SELECT i FROM Item i WHERE i.itemName = :itemName";
		
		return 6;
		
	}
	
	

}
