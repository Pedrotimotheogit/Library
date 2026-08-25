package SQL.Queries;

import Persistence.ConnectionUtil;
import SQL.Commands.ExecuteUpdate;

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

    READ(data -> ConnectionUtil.executeSelect("SELECT * FROM ? WHERE id = ?", data));

    private final ExecuteSelect executor;

    ExecuteQueries(ExecuteSelect executor) {
        this.executor = executor;
    }

    public List<Map<String, Object>> execute(String... data) {
        return executor.exec(data);
    }
}
