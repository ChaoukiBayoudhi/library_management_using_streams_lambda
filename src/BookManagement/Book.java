package BookManagement;

import Enumerations.BookType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data //defines getters; setters; equals; hashCode;toString and requiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {
    @EqualsAndHashCode.Include //2 books is equal if its have same isbnCode
    private int isbnCode;
    @NonNull //title is required
    private String title;
    @NonNull
    //@EqualsAndHashCode.Exclude //not to use the price to compare 2 books
    private BigDecimal price;
    private int stock;
    @NonNull
    private LocalDate releaseDate;
    @ToString.Exclude
    private byte[] coverImage;
    private String description;
    private BookType type;
}
