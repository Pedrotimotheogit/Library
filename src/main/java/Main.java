import Persistence.ConnectionUtil;
import UI.Menu;
import org.flywaydb.core.Flyway;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        //Try to connect to the database
        try (var connection = ConnectionUtil.getConnection()){
            System.out.println("Connection established successfully.");

            var flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres").schemas("public").load();
            flyway.migrate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Menu menu = new Menu(scanner);
    }
}
