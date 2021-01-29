package ru.job4j.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table if exists %s();", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add column if not exists %s %s;",
                    tableName,
                    columnName,
                    type);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s drop column if exists %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(TableEditor.class.getClassLoader().getResourceAsStream("app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("test1");
        tableEditor.addColumn("test1", "id", "serial primary key");
        tableEditor.addColumn("test1", "name", "varchar(100)");
        System.out.println(tableEditor.getScheme("test1"));
    }
}