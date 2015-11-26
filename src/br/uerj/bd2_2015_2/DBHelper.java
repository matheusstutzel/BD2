package br.uerj.bd2_2015_2;

import com.sun.istack.internal.NotNull;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Matheus on 20/10/2015.
 */
public class DBHelper {

    private static DBHelper instance;
    public Connection connection = null;

    private DBHelper() {

    }

    public synchronized static DBHelper getInstance() {
        if (instance == null)
            instance = new DBHelper();
        return instance;
    }

    public static String criaDelete(String table, ObservableList o, ObservableList<Object> column) {
        return criaDelete(table, o, convertColumn(column));
    }

    private static List convertColumn(ObservableList<Object> column) {
        ArrayList a = new ArrayList();
        for (Object o : column) {
            a.add(((TableColumn) o).getText());
        }
        return a;
    }

    public static String criaDelete(String table, List o, List column) {
        if (o.size() < column.size()) throw new IllegalArgumentException("Existem mais valores do que colunas");
        StringBuilder sb = new StringBuilder();
        sb.append("Delete from ").append(table).append(" Where ");
        for (int i = 0; i < o.size(); i++) {
            sb.append(column.get(i)).append("=\"").append(o.get(i)).append("\"");
            if (i + 1 < o.size()) sb.append(" and ");
        }
        return sb.toString();
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
        return select(convertSelect(table, "*", distinct, selection, selectionArgs, groupBy, having, orderBy, limit));
    }

    public ResultSet select(@NotNull String table, @NotNull String column, boolean distinct, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {
        return select(convertSelect(table, column, distinct, selection, selectionArgs, groupBy, having, orderBy, limit));
    }

    public String convertSelect(@NotNull String table, @NotNull String column, boolean distinct, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        StringBuilder b = new StringBuilder();
        b.append("SELECT ");
        if (distinct) b.append("DISTINCT ");
        b.append(column).append(" ");
        b.append("FROM ").append(table).append(" ");
        if (selection != null) b.append("WHERE ").append(convertArgs(selection, selectionArgs)).append(" ");
        if (groupBy != null) b.append("GROUPBY ").append(groupBy).append(" ");
        if (having != null) b.append("HAVING ").append(having).append(" ");
        if (orderBy != null) b.append("ORDER BY ").append(orderBy).append(" ");
        if (limit != null) b.append("LIMIT ").append(limit);
        System.out.println(b.toString());
        return b.toString();
    }

    public ResultSet select(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
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

    public boolean delete(String delete) throws SQLException {
        return connection.createStatement().executeUpdate(delete) > 0;
    }

    public boolean insert(String table, HashMap<String, String> value) throws Exception {
        StringBuilder sql = new StringBuilder(), values = new StringBuilder();
        sql.append("Insert into ").append(table).append(" (");
        values.append("(");
        int i = 0;
        Set<String> keys = value.keySet();
        for (String s : keys) {
            sql.append(s);
            values.append("\"").append(value.get(s)).append("\"");
            if (++i < keys.size()) {
                sql.append(",");
                values.append(",");
            }
        }
        sql.append(") Values ").append(values).append(")");
        System.out.println(sql.toString());
        return connection.createStatement().executeUpdate(sql.toString()) > 0;
        //return true;
    }
}
