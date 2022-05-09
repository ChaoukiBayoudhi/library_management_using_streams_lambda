package BookManagement;

import DBConnection.DataBaseConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Books {
    private List<Book> books;
    public Books()
    {
        books= DataBaseConnection.getAllBooks();
    }

    public List<Book> getBooksBetween(LocalDate start, LocalDate end)
    {
           return  books.stream()
                    .filter(b->b.getReleaseDate().isAfter(start)&&b.getReleaseDate().isBefore(end))
                    .toList();
    }
    public void showAuthorBooks(int id)
    {
        List<AuthorBook> lstAuthorBooks=DataBaseConnection.getAuthorBooks(id);
        lstAuthorBooks.stream().    //convert from list to Stream
                filter(x->x.getAuthor().getId()==id) //get only the object AuthorBook of the given author
                .map(x->x.getBooks().stream().map(Book::getTitle)) //get titles of all books of the given author
                .forEach(System.out::println);
//à revoir

    }
    public void borrowingCount()
    {
         long nbrBorrowings=DataBaseConnection.getAllBorrowings().stream()
                .filter(x->x.getBorrowingDate().isAfter(LocalDate.of(LocalDate.now().getYear(), 1,1)))
                .count();
        System.out.println(" count of borrowings = "+nbrBorrowings);
    }
}
