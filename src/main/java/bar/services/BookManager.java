package bar.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import bar.dao.BookDAO;
import bar.model.Item;

import java.util.Collection;


@Stateless
@Path("book")
public class BookManager {

    @Inject
    private BookDAO bookDAO;

    @Inject
    private UserContext userContext;

    @GET
    @Produces("application/json")
    public Collection<Item> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @GET
    @Path("{bookId}")
    @Produces("application/json")
    public Item getBook(@PathParam("bookId") String bookId) {
        return bookDAO.findById(Long.parseLong(bookId));
    }

    @PUT
    @Path("/borrow")
    public Response borrowBook(@QueryParam("bookId") String bookId) {
        Item bookToBorrow = bookDAO.findById(Long.parseLong(bookId));
        if (bookToBorrow != null) {
            bookDAO.borrowBook(bookToBorrow, userContext.getCurrentUser());
        }
        return Response.noContent().build();
    }

}
