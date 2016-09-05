package bar.dao;

import java.util.Calendar;
import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bar.model.Order;
import bar.model.Status;
import bar.model.User;

@Singleton
public class OrderDAO {

	@PersistenceContext
	private EntityManager em;

	public void addOrder(Order order) {
		order.calculateTotalPrice();
		order.setAcceptanceDay();
		order.setAcceptanceWeek();
		order.setAcceptanceMonth();
		em.persist(order);
	}

	public Collection<Order> getAllWaitingOrders() {
		TypedQuery<Order> query = em.createNamedQuery("findByStatus", Order.class).setParameter("status", "WAITING");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Collection<Order> getCurrentUserOrders(User user) {
		TypedQuery<Order> query = em.createNamedQuery("getAcceptedAndOverdue", Order.class)
				.setParameter("executor", user.getUserName()).setParameter("status1", Status.ACCEPTED)
				.setParameter("status2", Status.OVERDUE);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void setOrderAsAccepted(long id) {

		Status status = Status.WAITING;

		Query query = em.createQuery("UPDATE Order o SET o.status=:status WHERE o.id=:id");
		query.setParameter("status", status);
		query.setParameter("id", id);

		int updateCount = query.executeUpdate();
	}

	public void setOrderAsOverdue(long id) {

		Status status = Status.OVERDUE;

		Query query = em.createQuery("UPDATE Order o SET o.status=:status WHERE o.id=:id");
		query.setParameter("status", status);
		query.setParameter("id", id);

		int updateCount = query.executeUpdate();
	}

	public Order findById(long key) {
		return em.find(Order.class, key);
	}

	public float estimateDailyProfit() {

		Calendar now = Calendar.getInstance();

		int day = now.get(Calendar.DAY_OF_WEEK);// Sunday-1, Monday-2
		int week = now.get(Calendar.WEEK_OF_MONTH);// first week-2
		int month = now.get(Calendar.MONTH);// January-0
		Status status = Status.ACCEPTED;

		Query q = em.createQuery(
				"SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceDay=:day AND o.acceptanceWeek=:week AND o.acceptanceMonth=:month AND o.status=:status ");
		q.setParameter("day", day);
		q.setParameter("week", week);
		q.setParameter("month", month);
		q.setParameter("status", status);
		try {
			return (float) q.getSingleResult();
		} catch (Exception e) {
			return (Float) null;
		}

	}

	public float estimateWeeklyProfit() {

		Calendar now = Calendar.getInstance();

		int week = now.get(Calendar.WEEK_OF_MONTH);// first week-2
		int month = now.get(Calendar.MONTH);// January-0
		Status status = Status.ACCEPTED;

		Query q = em.createQuery(
				"SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceWeek=:week AND o.acceptanceMonth=:month AND o.status=:status ");
		q.setParameter("week", week);
		q.setParameter("month", month);
		q.setParameter("status", status);

		try {
			return (float) q.getSingleResult();
		} catch (Exception e) {
			return (Float) null;
		}

	}

	public float estimateMonthlyProfit() {

		Calendar now = Calendar.getInstance();

		int month = now.get(Calendar.MONTH);// January-0
		Status status = Status.ACCEPTED;

		Query q = em.createQuery(
				"SELECT SUM(o.totalPrice) FROM Order o WHERE o.acceptanceMonth=:month AND o.status=:status ");
		q.setParameter("month", month);
		q.setParameter("status", status);

		try {
			return (float) q.getSingleResult();
		} catch (Exception e) {
			return (Float) null;
		}

	}

}
