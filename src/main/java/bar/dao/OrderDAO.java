package bar.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bar.model.Order;
import bar.model.User;

@Singleton
public class OrderDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addOrder(Order order) {
		em.persist(order);
	}
	
	public Collection<Order> getCurrentOrders(User user) {
		
	}
	
	public Collection<Order> getAllWaitingOrders() {
		return em.createNamedQuery("findByStatusAndExecutor", Order.class).getResultList();
	}
}
