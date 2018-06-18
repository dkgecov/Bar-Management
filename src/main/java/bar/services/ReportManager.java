package bar.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
	
	/*@GET
	@Path("between")
	@Produces("application/json")
	public float estimateProfitBetweenTwoDates( List<Date> Dates ){
		return orderDAO.estimateProfitBetweenTwoDates(Dates.get(0),Dates.get(1));
		
	}*/
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String estimateProfitBetweenTwoDates( String dates){
		return "trtretr!";
		
	}

}
