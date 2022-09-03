package com.ita.edu.speakua.ui.utils.jdbc.dao;

import com.ita.edu.speakua.ui.utils.PropertyProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerDAO {
    private static volatile ManagerDAO instance = null;
    private final Map<Long, Connection> connections;
    private final String login;
    private final String password;
    private final String url;
    private final PropertyProvider property;

    private ManagerDAO() {
        connections = new HashMap<>();
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        property = new PropertyProvider();
        login = property.getJDBCLogin();
        password = property.getJDBCPassword();
        url = property.getJDBCUrl();
    }

    public static ManagerDAO getInstance() {
        if (instance == null) {
            synchronized (ManagerDAO.class) {
                if (instance == null) {
                    instance = new ManagerDAO();
                }
            }

        }
        return instance;
    }

    public static void closeAllConnection() {
        if (instance != null) {
            for (Map.Entry<Long, Connection> entry : instance.connections.entrySet()) {
                try {
                    entry.getValue().close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Connection getConnection() {
        Long idThread = Thread.currentThread().getId();
        Connection connection = connections.get(idThread);
        if (connection == null) {
            connection = createConnection();
            connections.put(idThread, connection);
        }
        return connection;
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public List<List<String>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getString(i));
            } result.add(row);
        } return result;
    }
}