package bar.dao;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton

public class ProfitDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public float estimateDailyProfit(){
		
		
		Calendar now = Calendar.getInstance();
		
		int day=now.get(Calendar.DAY_OF_WEEK);//Sunday-1, Monday-2
		int week=now.get(Calendar.WEEK_OF_MONTH);//first week-2 
		int month=now.get(Calendar.MONTH);//January-0
		

		Query q = em.createQuery ("SELECT SUM(o.totalPrice) FROM Order o WHERE o.getAcceptanceDay=:day AND o.getWeek=:week AND o.getMonth=:month ");
        
		try {
            return  (float) q.getSingleResult();
        } catch (Exception e) {
            return (Float) null;
        }
		
		
	}
	
	

}
