package UI;

import Exceptions.ValidOption;
import SQL.Commands.Commands;
import SQL.Queries.ExecuteQueries;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    public Menu(Scanner scanner) {
        String option;
        do {
            System.out.println("""
                Welcome to the Library Management System
                
                Select an option:
                1 - Register a new book or author
                2- Visualize all books and authors
                3- Update information
                4- Delete books
                5- Delete authors
                6- Exit
                """);
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    //Enters the other menu where you can select what you want to create (book or author)
                    String optionToCreate;

                    System.out.println("""
                            Registering a new book or author
                            1 - Add a new book
                            2 - Add a new author
                            3-  Add a new book to be borrowed""");
                    optionToCreate = scanner.nextLine();

                    if (!optionToCreate.matches("[1-3]")) {
                        throw new ValidOption();
                    }

                    switch (optionToCreate) {
                        case "1":
                            // Handle register book option
                            ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);
                            System.out.print("Book title: ");
                            String title = scanner.nextLine();

                            System.out.print("Quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            System.out.print("Release date (YYYY-MM-DD): ");
                            LocalDate releaseDate = LocalDate.parse(scanner.nextLine());

                            Commands.CREATE_NEW_BOOK.execute(
                                    title,
                                    quantity,
                                    releaseDate
                            );
                            break;

                        case "2":
                            // Handle register author option
                            System.out.println("Author name: ");
                            String authorName = scanner.nextLine();

                            Commands.CREATE_NEW_AUTHOR.execute(authorName);
                            break;
                        case "3":
                            // Handle register borrowed book option
                            System.out.println("Book title: ");
                            String bookTitle = scanner.nextLine();

                            System.out.println("Reader name: ");
                            String readerName = scanner.nextLine();

                            System.out.println("Date it was borrowed (YYYY-MM-DD): ");
                            LocalDate borrowDate = LocalDate.parse(scanner.nextLine());

                            System.out.println("Date it was returned (YYYY-MM-DD): ");
                            String returnDate = scanner.nextLine();

                            Commands.CREATE_NEW_BORROWED_BOOK.execute(bookTitle, readerName, borrowDate, returnDate.isEmpty() || returnDate.equalsIgnoreCase("null") ? null : LocalDate.parse(returnDate));
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case "2":
                    // Handle visualize option
                    System.out.println("Choose a table to see");
                    System.out.println("1 - Table books");
                    System.out.println("2 - Table authors");
                    optionToCreate = scanner.nextLine();


                    if (!optionToCreate.matches("[1-2]")) {
                        throw new ValidOption();
                    }

                    switch (optionToCreate) {
                        case "1":
                            ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);
                            break;
                        case "2":
                            ExecuteQueries.SHOW_ALL_AUTHORS.execute().forEach(System.out::println);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                    break;
                case "3":
                    // Handle update option
                    System.out.println("""
                            Which table you want to update?
                            1- Table books
                            2- Table authors
                            3- Table book_authors
                            4- Table borrowed_books
                            5- Table readers""");
                    optionToCreate = scanner.nextLine();

                    if (!optionToCreate.matches("[1-5]")) {
                        throw new ValidOption();
                    }

                    switch (optionToCreate) {
                        case "1":
                            // Handle update books option
                            System.out.println("""
                                    What do you want to update?
                                    1 - Title
                                    2 - Quantity
                                    3 - Release Date
                                    """);
                            optionToCreate = scanner.nextLine();

                            if (!optionToCreate.matches("[1-5]")) {
                                throw new ValidOption();
                            }

                            switch (optionToCreate) {
                                case "1":
                                    //Show all books
                                    ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);

                                    //Select which book to update
                                    System.out.println("Which is the book you want to update?");
                                    int bookId = Integer.parseInt(scanner.nextLine());
                                    System.out.println("What is the new title?");
                                    String newTitle = scanner.nextLine();
                                    Commands.UPDATE_BOOK_TITLE.execute(newTitle, bookId);

                                case "2":
                                    //Show all books
                                    ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);

                                    //Select which book to update
                                    System.out.println("Which is the book you want to update?");
                                    bookId = Integer.parseInt(scanner.nextLine());
                                    //Add the selected quantity
                                    System.out.println("How much books you want to add?");
                                    int newQuantity = Integer.parseInt(scanner.nextLine());
                                    Commands.UPDATE_ADD_BOOK_QUANTITY.execute(newQuantity, bookId);

                                case "3":
                                    //Show all books
                                    ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);

                                    System.out.println("Which is the book you want to update?");
                                    bookId = Integer.parseInt(scanner.nextLine());

                                    System.out.println("What's the new date? (YYYY-MM-DD)");
                                    LocalDate newReleaseDate = LocalDate.parse(scanner.nextLine());

                                    Commands.UPDATE_BOOK_RELEASE_DATE.execute(newReleaseDate, bookId);

                            }

                            break;
                        case "2":
                            // Handle update authors option
                            ExecuteQueries.SHOW_ALL_AUTHORS.execute().forEach(System.out::println);
                            System.out.println("Select the id of the author you want to update:");
                            int authorId = Integer.parseInt(scanner.nextLine());
                            System.out.println("What's the author's new name?");
                            String newAuthorName = scanner.nextLine();
                            Commands.UPDATE_AUTHOR_NAME.execute(newAuthorName, authorId);

                        case "3":
                            // Handle update book_authors option
                            System.out.println("""
                                    =================================
                                          BOOKS WITHOUT AUTHORS
                                    =================================""");
                            ExecuteQueries.SHOW_BOOKS_WITHOUT_AUTHORS.execute().forEach(System.out::println);

                            System.out.println("Select which book to add a author to:");
                            int bookId = Integer.parseInt(scanner.nextLine());

                            ExecuteQueries.SHOW_ALL_AUTHORS.execute().forEach(System.out::println);

                            System.out.println("Select the id of the author to add:");

                            System.out.println("If you can't find the author, type 'author' to add one");
                            String author = scanner.nextLine();

                            if(author.equals("author")) {
                                System.out.println("Author name: ");
                                String newAuthor = scanner.nextLine();

                                Commands.CREATE_NEW_AUTHOR.execute(newAuthor);
                            }

                            ExecuteQueries.SHOW_ALL_AUTHORS.execute().forEach(System.out::println);

                            System.out.println("Select the id of the author to add:");
                            authorId = Integer.parseInt(scanner.nextLine());
                            Commands.UPDATE_BOOK_AUTHORS.execute(bookId, authorId);
                        case "4":
                            // Handle update borrowed_books option
                            System.out.println("""
                                    =================================
                                             BORROWED BOOKS
                                    =================================""");

                            ExecuteQueries.SHOW_ALL_BORROWED_BOOKS.execute().forEach(System.out::println);

                            System.out.println("Select which book to update the return date for:");
                            bookId = Integer.parseInt(scanner.nextLine());

                            System.out.println("Select the reader ID:");
                            int readerId = Integer.parseInt(scanner.nextLine());

                            System.out.println("Enter the return date (YYYY-MM-DD):");
                            String returnDate = scanner.nextLine();
                            Commands.UPDATE_BORROWED_BOOK.execute(returnDate, bookId, readerId);

                        case "5":
                            // Handle update readers option
                            ExecuteQueries.SHOW_ALL_READERS.execute().forEach(System.out::println);
                            System.out.println("Which reader do you want to update?");
                            readerId = Integer.parseInt(scanner.nextLine());

                            System.out.println("Enter the new name for the reader:");
                            String newReaderName = scanner.nextLine();

                            Commands.UPDATE_READER_NAME.execute(newReaderName, readerId);
                    }

                    break;
                case "4":
                    // Handle delete books option
                    ExecuteQueries.SHOW_ALL_BOOKS.execute().forEach(System.out::println);
                    System.out.println("Which book you want to remove?");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    Commands.DELETE_BOOK.execute(bookId);
                    break;
                case "5":
                    // Handle delete authors option
                    ExecuteQueries.SHOW_ALL_AUTHORS.execute().forEach(System.out::println);
                    System.out.println("Which author you want to remove?");
                    int authorId = Integer.parseInt(scanner.nextLine());
                    Commands.DELETE_AUTHOR.execute(authorId);
                    break;
                case "6":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!option.equals("6"));

    }
}
