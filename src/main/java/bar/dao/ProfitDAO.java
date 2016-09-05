package bar.dao;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bar.model.Status;

@Singleton

public class ProfitDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	
	
	public float estimateDailyProfit(){
		
		
		Calendar now = Calendar.getInstance();
		
		int day=now.get(Calendar.DAY_OF_WEEK);//Sunday-1, Monday-2
		int week=now.get(Calendar.WEEK_OF_MONTH);//first week-2 
		int month=now.get(Calendar.MONTH);//January-0
		Status status=Status.ACCEPTED;

		Query q = em.createQuery ("SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceDay=:day AND o.acceptanceWeek=:week AND o.acceptanceMonth=:month AND o.status=:status ");
        
		try {
            return  (float) q.getSingleResult();
        } catch (Exception e) {
            return (Float) null;
        }
		
		
	}
	
	
	
	
	
	public float estimateWeeklyProfit(){
		
		
		Calendar now = Calendar.getInstance();
		
		int week=now.get(Calendar.WEEK_OF_MONTH);//first week-2 
		int month=now.get(Calendar.MONTH);//January-0
		Status status=Status.ACCEPTED;

		Query q = em.createQuery ("SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceWeek=:week AND o.acceptanceMonth=:month AND o.status=:status ");
        
		try {
            return  (float) q.getSingleResult();
        } catch (Exception e) {
            return (Float) null;
        }
		
		
	}
	
	
	
	
	
	public float estimateMonthlyProfit(){
		
		
		Calendar now = Calendar.getInstance();
		
		
		int month=now.get(Calendar.MONTH);//January-0
		Status status=Status.ACCEPTED;

		Query q = em.createQuery ("SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceMonth=:month AND o.status=:status ");
        
		try {
            return  (float) q.getSingleResult();
        } catch (Exception e) {
            return (Float) null;
        }
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
