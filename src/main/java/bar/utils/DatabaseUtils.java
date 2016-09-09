package bar.utils;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bar.dao.ItemDAO;
import bar.dao.UserDAO;
import bar.model.Item;
import bar.model.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("test", "test", "test.user@somemail.com",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com",
                    new Date())};

    private static Item[] ITEMS = {
            new Item("Chicken", "3.60",
                    "Hot Meal", "Chicken fillet with 2 salads"),
            new Item("Zagorka", "1.60", "Beer", "Alcholic bavarage"),
            new Item("Smirnoff", "2", "Vodka", "Alcholic bavarage"),
            new Item("Smirnoff", "2", "Vodka", "Alcholic bavarage")};

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ItemDAO itemDAO;
    
    @EJB
    private UserDAO userDAO;
    
    public void addTestDataToDB() {
    	deleteData();
        addTestUsers();
        addTestItems();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM Item").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }

    private void addTestItems() {
        for (Item item : ITEMS) {
            itemDAO.addItem(item);
        }
    }
}
