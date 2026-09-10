package SQL.Queries;

import Persistence.ConnectionUtil;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;

public enum ExecuteQueries {
    SHOW_BOOKS_WITHOUT_AUTHORS(data -> {
        String sql = """
                SELECT b.book_id, b.title
                FROM books b
                LEFT JOIN book_authors ba
                    ON b.book_id = ba.book_id
                WHERE ba.book_id IS NULL;""";

        return ConnectionUtil.executeSelect(sql, data);
    }),

    SHOW_ALL_BORROWED_BOOKS (data -> ConnectionUtil.executeSelect("SELECT * FROM borrowed_books", data)),

    SHOW_ALL_AUTHORS(data -> ConnectionUtil.executeSelect("SELECT * FROM authors", data)),

    SHOW_ALL_BOOKS(data -> {
       return ConnectionUtil.executeSelect("SELECT book_id, title, quantity, release_date FROM books ORDER BY book_id", data);
    }),

    SHOW_AUTHOR_FOR_ID (data -> {
        return ConnectionUtil.executeSelect("SELECT * FROM authors WHERE author_id = ?", data);
    }),

    SHOW_ALL_READERS(data -> ConnectionUtil.executeSelect("SELECT * FROM readers"));



    private final ExecuteSelect executor;

    ExecuteQueries(ExecuteSelect executor) {
        this.executor = executor;
    }

    public List<Map<String, Object>> execute(Object... data) {
        return executor.exec(data);
    }
}
