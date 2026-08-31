package Persistence;

import lombok.NoArgsConstructor;
import java.sql.*;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {
        public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres");
        }

    public static int executeUpdate(String query, Object[] data) {
        // Implementation for executing update statements
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {

            for (int i = 0; i < data.length; i++) {
                statement.setObject(i + 1, data[i]);
            }

            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> executeSelect(String query, Object... data) {
        List<Map<String, Object>> result = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {

            for (int i = 0; i < data.length; i++) {
                statement.setObject(i + 1, data[i]);
            }

            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metadata.getColumnName(i);
                    Object value = resultSet.getObject(i);

                    row.put(columnName, value);
                }

                result.add(row);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
