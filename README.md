# Library management using streams lambda

An example of application using Stream APi, Lambda Expression and postgresql.

## Tech stack

- Java
- Lombok

## Concepts covered

Stream API, collections, console input (Scanner), exception handling, lambda expressions, static/final members.

## Project structure

- `Application`: Main
- `AuthorManagement`: Author
- `BookManagement`: AuthorBook, Book, Books
- `BorrowingPackage`: Borrowing
- `DBConnection`: DataBaseConnection
- `Enumerations`: BookType

## How to run

Open the folder in IntelliJ IDEA or Eclipse and run `Main` (it contains the `main` method).

The code uses Lombok annotations: add the Lombok library and enable annotation processing in your IDE.

The JDBC code expects a running database server; set your own connection URL, username and password before running.

---

Author: [Chaouki Bayoudhi](https://github.com/ChaoukiBayoudhi)
