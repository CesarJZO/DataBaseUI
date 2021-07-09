package db;
import javax.swing.*;
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

    private void execute(String query) {
        try {
            rst = stmt.executeQuery(query);
            System.out.println(rst);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, throwables.getMessage());
        }
    }

    public void insertIntoDevs(String id, String name, String fk) {
        String query = "insert into Developers values(" + id + ", '" + name + "', " + fk + ")";
        execute(query);
    }

    public void insertIntoPubs(String id, String name) {
        String query = "insert into Publishers values(" + id + ", '" + name + "')";
        execute(query);
    }

    public void update(String table, String column, Object value) {
        String query = "update " + table + " set " + column + " = " + value;
        execute(query);
    }

    public void delete(String table, String whereColumn, String whereVal) {
        String query = "delete from " + table + " where " + whereColumn + " = " + whereVal;
        execute(query);
    }
}
