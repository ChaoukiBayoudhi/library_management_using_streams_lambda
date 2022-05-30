package AuthorManagement;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(exclude ={"description","photo"}) //show all except description and photo
public class Author {
    private int id;
   // @ToString.Include
    private String firstName;
   // @ToString.Include
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private String description;
    //@ToString.Exclude
    private byte[] photo;
}
