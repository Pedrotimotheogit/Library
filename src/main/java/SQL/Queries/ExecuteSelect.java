package SQL.Queries;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface ExecuteSelect {
    List<Map<String, Object>> exec(String... data);
}
