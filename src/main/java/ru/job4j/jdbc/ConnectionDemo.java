package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException,
            IOException {
        Properties prs = new Properties();
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        try (InputStream in = classLoader.getResourceAsStream("app.properties")){
            prs.load(in);
        }
        Class.forName("org.postgresql.Driver");
        String url = prs.getProperty("url");
        String login = prs.getProperty("login");
        String password = prs.getProperty("password");
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
