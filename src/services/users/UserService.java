package services.users;

import models.users.Administrateur;
import models.users.Caissier;
import models.users.Client;
import models.users.User;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Service<User> {
    private DatabaseConnection connection;
    private static UserService instance;

    private UserService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User selectOne(int id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return filter("1 = 1");
    }

    @Override
    public List<User> filter(String whereQuery) {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                User user = null;

                if (resultSet.getString("role").equals("admin")) {
                    user = new Administrateur(resultSet.getString("username"), resultSet.getString("first_name"),
                            resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));

                } else if (resultSet.getString("role").equals("client")) {
                    user = new Client(resultSet.getString("username"), resultSet.getString("first_name"),
                            resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));
                } else {
                    user = new Caissier(resultSet.getString("username"), resultSet.getString("first_name"),
                            resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"));
                }

                user.setId(resultSet.getInt("id"));

                users.add(user);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}
