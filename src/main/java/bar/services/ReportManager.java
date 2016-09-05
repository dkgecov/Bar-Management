package bar.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import bar.dao.ProfitDAO;

@Stateless
@Path("report")
public class ReportManager {
	@Inject 
	private ProfitDAO profitDAO;
	
	
	@GET
	@Path("daily")
	@Produces("application/json")
	public float getDailyProfit(){
		return profitDAO.estimateDailyProfit();
		
	}
	
	
	@GET
	@Path("weekly")
	@Produces("application/json")
	public float getWeeklyProfit(){
		return profitDAO.estimateWeeklyProfit();
		
	}
	@GET
	@Path("monthly")
	@Produces("application/json")
	public float getMonthlyProfit(){
		return profitDAO.estimateMonthlyProfit();
		
	}
	
	
	
	

}
