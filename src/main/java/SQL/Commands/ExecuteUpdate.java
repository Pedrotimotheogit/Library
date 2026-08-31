package SQL.Commands;

@FunctionalInterface
public interface ExecuteUpdate {
    int exec(Object... data);
}
