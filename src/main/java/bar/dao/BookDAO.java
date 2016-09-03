package bar.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bar.model.Item;
import bar.model.User;

@Singleton
public class BookDAO {

    @PersistenceContext
    private EntityManager em;

    public void addBook(Item book) {
        Item foundBook = findByAuthorAndTitle(book.getAuthor(), book.getTitle());
        if (foundBook == null) {
            em.persist(book);
        } else {
            int currentAmount = foundBook.getAmount() + 1;
            foundBook.setAmount(currentAmount);
        }
    }

    public Collection<Item> getAllBooks() {
        return em.createNamedQuery("getAllBooks", Item.class).getResultList();
    }

    public Item findById(long key) {
        return em.find(Item.class, key);
    }

    public Item findByAuthorAndTitle(String author, String title) {
        TypedQuery<Item> query = em
                .createNamedQuery("findByAuthorAndTitle", Item.class)
                .setParameter("author", author).setParameter("title", title);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void borrowBook(Item bookToBorrow, User userWhoTakesTheBook) {
        Item foundBook = findById(bookToBorrow.getId());
        int newAmount = foundBook.getAmount() - 1;
        foundBook.setAmount(newAmount);
        userWhoTakesTheBook.getCurrentBooks().add(foundBook);
    }

    public void returnBook(Item book, User user) {
        Item foundBook = findById(book.getId());
        int newAmount = book.getAmount() + 1;
        foundBook.setAmount(newAmount);
        User userWhoReturnsTheBook = em.find(User.class, user.getId());
        userWhoReturnsTheBook.getCurrentBooks().remove(foundBook);
    }

}
