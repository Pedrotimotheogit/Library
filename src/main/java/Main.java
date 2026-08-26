import Persistence.ConnectionUtil;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //Try to connect to the database
        try (var connection = ConnectionUtil.getConnection()){
            System.out.println("Connection established successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        var flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres").load();
        flyway.migrate();

    }
}
