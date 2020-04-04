package services.users;

import models.users.Client;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements Service<Client> {

    private static final String ROLE = "client";
    private DatabaseConnection connection;
    private static ClientService instance;

    private ClientService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static ClientService getInstance() {
        if (instance == null)
            instance = new ClientService();

        return instance;
    }

    @Override
    public Client create(Client object) {

        String query = String.format("INSERT INTO users(first_name, last_name, username, email, password, role) VALUES('%s', '%s', '%s', '%s', '%s', '%s')",
                                    object.getFirstName(), object.getLastName(), object.getUsername(), object.getEmail(), object.getPassword(), ROLE);

        connection.nonSelectQuery(query);

        query = "SELECT * FROM users ORDER BY id DESC";

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next())
                object.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return object;
    }

    @Override
    public void update(Client object) {

        String query = String.format("UPDATE users SET first_name = '%s', last_name = '%s', username = '%s', email = '%s', password = '%s' WHERE id = %d",
                object.getFirstName(), object.getLastName(), object.getUsername(), object.getEmail(), object.getPassword(), object.getId());

        connection.nonSelectQuery(query);

    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM users WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public Client selectOne(int id) {

        String query = String.format("SELECT * FROM users WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Client client = new Client(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                client.setId(resultSet.getInt("id"));

                return client;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Client> selectAll() {

        return filter("role = '" + ROLE + "'");

    }

    @Override
    public List<Client> filter(String whereQuery) {

        List<Client> clients = new ArrayList<>();

        String query = "SELECT * FROM users WHERE role = '" + ROLE + "' and " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Client client = new Client(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                client.setId(resultSet.getInt("id"));

                clients.add(client);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }
}
