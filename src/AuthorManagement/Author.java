package AuthorManagement;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private String description;
    private byte[] photo;
}
