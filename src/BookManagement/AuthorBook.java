package BookManagement;

import AuthorManagement.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class AuthorBook {
    private Author author; //the author
    private Set<Book> books = new HashSet<>();//Set of its books
}
