package SQL.Commands;

import Persistence.ConnectionUtil;

public enum Commands {
    /*
    CREATE insert new data into the database
    READ show data from the database
    UPDATE update existing data in the database
    DELETE delete data from the database
     */

    // You can select which table to insert, select, update and delete
    CREATE_NEW_AUTHOR(data ->{
        String sql = "INSERT INTO authors (name) VALUES ?";

        return ConnectionUtil.executeUpdate(sql, data);
    })
    ,
    CREATE_NEW_BOOK(data -> {
        String sql = "INSERT INTO books (title, quantity, release_date) VALUES (?, ?, ?)";
        return ConnectionUtil.executeUpdate(sql, data);
    }),


    UPDATE(data -> ConnectionUtil.executeUpdate("UPDATE ? SET column1 = ?, column2 = ? WHERE id = ?", data)),

    DELETE(data -> ConnectionUtil.executeUpdate("DELETE FROM ? WHERE id = ?", data));

    private final ExecuteUpdate executor;

    Commands(ExecuteUpdate executor) {
        this.executor = executor;
    }

    public int execute(String... data) {
        return executor.exec(data);
    }
}
