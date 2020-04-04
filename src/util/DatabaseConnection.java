package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {

        if (instance == null)
            instance = new DatabaseConnection();

        return instance;
    }

    private void connect() {

        Properties props = new Properties();

        try {
            File configFile = new File("config.xml");
            FileInputStream in = new FileInputStream(configFile);
            props.loadFromXML(in);

            // Get the properties
            String url = props.getProperty("DATABASE_URL", "");
            String username = props.getProperty("DATABASE_USERNAME", "");
            String password = props.getProperty("DATABASE_PASSWORD", "");


            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ResultSet selectQuery(String query) {

        connect();
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    public void nonSelectQuery(String query) {

        connect();

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
