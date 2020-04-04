package services.users;

import models.users.Administrateur;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrateurService implements Service<Administrateur> {

    private static final String ROLE = "admin";
    private static AdministrateurService instance;
    private DatabaseConnection connection;

    private AdministrateurService() {
        connection = DatabaseConnection.getInstance();
    }

    public static AdministrateurService getInstance() {
        if (instance == null)
            instance = new AdministrateurService();

        return instance;
    }

    @Override
    public Administrateur create(Administrateur object) {
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
    public void update(Administrateur object) {
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
    public Administrateur selectOne(int id) {
        String query = String.format("SELECT * FROM users WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Administrateur administrateur = new Administrateur(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                administrateur.setId(resultSet.getInt("id"));

                return administrateur;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Administrateur> selectAll() {
        return filter("role = '" + ROLE + "'");
    }

    @Override
    public List<Administrateur> filter(String whereQuery) {
        List<Administrateur> administrateurs = new ArrayList<>();

        String query = "SELECT * FROM users WHERE role = '" + ROLE + "' and " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Administrateur administrateur = new Administrateur(resultSet.getString("username"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                administrateur.setId(resultSet.getInt("id"));

                administrateurs.add(administrateur);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return administrateurs;
    }
}
