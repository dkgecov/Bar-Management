package bar.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bar.model.Order;

@Singleton
public class OrderDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addOrder(Order order) {
		em.persist(order);
	}
	
	public Collection<Order> getAllOrders() {
		return em.createNamedQuery("getAllOrders", Order.class)
	}
}
