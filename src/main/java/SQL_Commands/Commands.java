package SQL_Commands;

import Persistence.ConnectionUtil;

public enum Commands {
    /*
    CREATE insert new data into the database
    READ show data from the database
    UPDATE update existing data in the database
    DELETE delete data from the database
     */

    // You can select which table to insert, select, update and delete
    CREATE(data -> ConnectionUtil.executeUpdate("INSERT INTO ? VALUES (?, ?, ?)", data)),
    READ(data -> ConnectionUtil.executeSelect("SELECT * FROM ? WHERE id = ?", data)),
    UPDATE(data -> ConnectionUtil.executeUpdate("UPDATE ? SET column1 = ?, column2 = ? WHERE id = ?", data)),
    DELETE(data -> ConnectionUtil.executeUpdate("DELETE FROM ? WHERE id = ?", data));

    private final ExecuteCommands executor;

    Commands(ExecuteCommands executor) {
        this.executor = executor;
    }

    public int execute(String... data) {
        return executor.exec(data);
    }
}
