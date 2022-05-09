package BorrowingPackage;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Borrowing {
    private int id;
    private LocalDate borrowingDate;
    private int duration;
    private String status;
}
