package bar.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import bar.dao.OrderDAO;


@Stateless
@Path("report")
public class ReportManager {
	@Inject 
	private OrderDAO orderDAO;
	
	
	@GET
	@Path("daily")
	@Produces("application/json")
	public float getDailyProfit(){
		return orderDAO.estimateDailyProfit();
		
	}
	
	
	@GET
	@Path("weekly")
	@Produces("application/json")
	public float getWeeklyProfit(){
		return orderDAO.estimateWeeklyProfit();
		
	}
	@GET
	@Path("monthly")
	@Produces("application/json")
	public float getMonthlyProfit(){
		return orderDAO.estimateMonthlyProfit();
		
	}
	
	
	
	

}
