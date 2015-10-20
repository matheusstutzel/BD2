package br.uerj.bd2_2015_2;

import com.sun.istack.internal.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Matheus on 20/10/2015.
 */
public class DBHelper {

    private static DBHelper instance;
    private Connection connection = null;

    private DBHelper() {

    }

    public synchronized static DBHelper getInstance() {
        if (instance == null)
            instance = new DBHelper();
        return instance;
    }

    public java.sql.Connection getConnection(String serverName, String mydatabase, String username, String password) {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver especificado não foi encontrado");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível conectar ao Banco de Dados");
            return null;
        }
    }

    public boolean closeConnection() {
        if (connection == null) return true;
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet select(@NotNull String table, boolean distinct, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        StringBuilder b = new StringBuilder();
        b.append("SELECT * ");
        if (distinct) b.append("DISTINCT ");
        b.append("FROM ").append(table).append(" ");
        if (selection != null) b.append("WHERE ").append(convertArgs(selection, selectionArgs)).append(" ");
        if (groupBy != null) b.append("GROUPBY ").append(groupBy).append(" ");
        if (having != null) b.append("HAVING ").append(having).append(" ");
        if (orderBy != null) b.append("ORDER BY ").append(orderBy).append(" ");
        if (limit != null) b.append("LIMIT ").append(limit);
        return connection.createStatement().executeQuery(b.toString());
    }

    private String convertArgs(String clause, String[] args) {
        if (clause == null || args == null) return clause;
        for (String arg : args) {
            clause = clause.replaceFirst("\\?", getSQLString(arg));
        }
        return clause;
    }

    private String getSQLString(Object o) {
        if (o instanceof String) return "\'" + o + "\'";
        else return o.toString();
    }
}
