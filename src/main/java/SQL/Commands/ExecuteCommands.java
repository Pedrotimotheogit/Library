package SQL.Commands;

@FunctionalInterface
public interface ExecuteCommands {
    int exec(Object... data);
}
