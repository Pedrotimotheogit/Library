package SQL.Commands;

import Persistence.ConnectionUtil;

public enum Commands {
    /*
    CREATE insert new data into the database
    READ show data from the database
    UPDATE update existing data in the database
    DELETE delete data from the database
     */

    //Create new Author, book or reader
    CREATE_NEW_AUTHOR(data ->{
        String sql = "INSERT INTO authors (author_name) VALUES (?)";

        return ConnectionUtil.executeUpdate(sql, data);
    })
    ,
    CREATE_NEW_BOOK(data -> {
        String sql = "INSERT INTO books (title, quantity, release_date) VALUES (?, ?, ?)";
        return ConnectionUtil.executeUpdate(sql, data);
    }),

    CREATE_NEW_READER(data -> ConnectionUtil.executeUpdate("INSERT INTO readers (reader_name) VALUES (?)", data)),

    CREATE_NEW_BORROWED_BOOK(data -> ConnectionUtil.executeUpdate("INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date) VALUES (?, ?, ?, ?)", data)),

    REMOVE_QUANTITY_FROM_BOOK(data -> ConnectionUtil.executeUpdate("UPDATE books SET quantity = quantity - 1 WHERE book_id = ?", data)),

    //Update Books
    UPDATE_BOOK_TITLE(data -> ConnectionUtil.executeUpdate("UPDATE books SET title = ? WHERE book_id = ?", data)),

    UPDATE_ADD_BOOK_QUANTITY(data -> ConnectionUtil.executeUpdate("UPDATE books SET quantity = quantity + ? WHERE book_id = ?", data)),

    UPDATE_BOOK_RELEASE_DATE(data -> ConnectionUtil.executeUpdate("UPDATE books SET release_date = ? WHERE book_id = ?", data)),

    UPDATE_BOOK_AUTHORS(data -> ConnectionUtil.executeUpdate("INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)", data)),

    //Update Authors
    UPDATE_AUTHOR_NAME(data -> ConnectionUtil.executeUpdate("UPDATE authors SET author_name = ? WHERE author_id = ?", data)),

    //Update Readers
    UPDATE_READER_NAME(data -> ConnectionUtil.executeUpdate("UPDATE readers SET reader_name = ? WHERE reader_id = ?", data)),

    UPDATE_BORROWED_BOOK(data -> ConnectionUtil.executeUpdate("UPDATE borrowed_books SET return_date = ? WHERE book_id = ? AND reader_id = ?", data)),

    //Deletes
    DELETE_BOOK(data -> ConnectionUtil.executeUpdate("DELETE FROM books WHERE book_id = ?", data)),
    DELETE_AUTHOR(data -> ConnectionUtil.executeUpdate("DELETE FROM authors WHERE author_id = ?", data)),
    DELETE_READER(data -> ConnectionUtil.executeUpdate("DELETE FROM readers WHERE reader_id = ?", data));

    private final ExecuteCommands executor;

    Commands(ExecuteCommands executor) {
        this.executor = executor;
    }

    public int execute(Object... data) {
        return executor.exec(data);
    }
}
