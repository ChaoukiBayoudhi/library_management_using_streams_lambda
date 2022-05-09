package BookManagement;

import Enumerations.BookType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {
    @EqualsAndHashCode.Include
    private int isbnCode;
    private String title;
    private BigDecimal price;
    private int stock;
    private LocalDate releaseDate;
    @ToString.Exclude
    private byte[] coverImage;
    private String description;
    private BookType type;
}
