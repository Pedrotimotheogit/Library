package SQL_Commands;



@FunctionalInterface
public interface ExecuteCommands {
    int exec(String... data);
}
