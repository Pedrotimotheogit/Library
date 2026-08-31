import Persistence.ConnectionUtil;
import UI.Menu;
import org.flywaydb.core.Flyway;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Try to connect to the database
        try (var connection = ConnectionUtil.getConnection()){
            System.out.println("Connection established successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        var flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres").load();
        flyway.migrate();

        Menu menu = new Menu();
        menu.showMenu(scanner);
    }
}
