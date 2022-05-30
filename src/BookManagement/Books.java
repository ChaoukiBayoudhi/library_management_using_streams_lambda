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
           return  books.stream() //convert from List to Stream
                    .filter(b->b.getReleaseDate().isAfter(start)&&b.getReleaseDate().isBefore(end))
                   //the filter result is a Stream contains only elements that match the right part of the lambda Expression
                    .toList(); //convert from Stream to List
    }
    //show titles of books of a given Author id.
    public void showAuthorBooks(int id)
    {
        List<AuthorBook> lstAuthorBooks=DataBaseConnection.getAuthorBooks(id);
        lstAuthorBooks.stream()   //convert from list to Stream
                //.filter(x->x.getAuthor().getId()==id) //get only the object AuthorBook of the given author
                .map(x->x.getBooks().stream()//se of Books
                        .map(Book::getTitle)) //replaces each Book by its title

                .forEach(System.out::println);//show books' titles on the screen


    }
    public void borrowingCount()
    {
         long nbrBorrowings=DataBaseConnection.getAllBorrowings().stream()
                .filter(x->x.getBorrowingDate().isAfter(LocalDate.of(LocalDate.now().getYear(), 1,1)))
                .count();
        System.out.println(" count of borrowings = "+nbrBorrowings);
    }
}
