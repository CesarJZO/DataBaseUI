package db;
import java.sql.*;

/**
 * Constructs and executes all the required queries
 */
public class Queries {
    private final Bridge bridge;
    private Connection conn;
    private Statement stmt;
    private ResultSet rst;

    /**
     * Sets a connection with the specified user
     * @param user The username
     * @param password Its password
     */
    public Queries(String user, String password) {
        bridge = new Bridge();
        try {
            conn = bridge.getConnection(user, password);
            stmt = conn.createStatement();
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
        }
    }

    private String execute(String query) {
        try {
            rst = stmt.executeQuery(query);
            return rst.toString();
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }

    public String insertIntoDevs(String id, String name, String fk) {
        String query = "insert into Developers values(" + id + ", '" + name + "', " + fk + ")";
        return execute(query);
    }

    public String insertIntoPubs(String id, String name) {
        String query = "insert into Publishers values(" + id + ", '" + name + "')";
        return execute(query);
    }

    public String update(String table, String column, Object value) {
        String query = "update " + table + " set " + column + " = " + value;
        return execute(query);
    }

    public String delete(String table, String whereColumn, String whereVal) {
        String query = "delete from " + table + " where " + whereColumn + " = " + whereVal;
        return execute(query);
    }

    public void closeBridge() {
        bridge.closeAll(rst, stmt, conn);
    }
}
