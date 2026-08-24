package Persistence;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {
        public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres");
        }

    public static int executeUpdate(String s, String[] data) {
        // Implementation for executing update statements
        return 0;
    }

    public static int executeSelect(String query, String[] data) {
        return 0;
    }
}
