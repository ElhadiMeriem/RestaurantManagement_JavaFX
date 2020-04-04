package services.users;

import models.users.Caissier;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaissierService implements Service<Caissier> {

    private static final String ROLE = "caissier";
    private DatabaseConnection connection;
    private static CaissierService instance;

    private CaissierService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static CaissierService getInstance() {
        if (instance == null)
            instance = new CaissierService();

        return instance;
    }

    @Override
    public Caissier create(Caissier object) {

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
    public void update(Caissier object) {

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
    public Caissier selectOne(int id) {

        String query = String.format("SELECT * FROM models.users WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Caissier caissier = new Caissier(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                caissier.setId(resultSet.getInt("id"));

                return caissier;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Caissier> selectAll() {
        return filter("role = '" + ROLE + "'");
    }

    @Override
    public List<Caissier> filter(String whereQuery) {
        List<Caissier> caissiers = new ArrayList<>();

        String query = "SELECT * FROM users WHERE role = '" + ROLE + "' and " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Caissier caissier = new Caissier(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                caissier.setId(resultSet.getInt("id"));

                caissiers.add(caissier);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return caissiers;
    }
}
