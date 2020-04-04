package services.items;

import models.boissons.Boisson;
import models.boissons.BoissonFactory;
import models.boissons.BoissonType;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoissonService implements Service<Boisson> {

    private static final String TYPE = "Boisson";
    private DatabaseConnection connection;
    private static BoissonService instance;

    private BoissonService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static BoissonService getInstance() {
        if (instance == null)
            instance = new BoissonService();

        return instance;
    }

    @Override
    public Boisson create(Boisson object) {
        String query = String.format("INSERT INTO items(description, price, type) VALUES('%s', %f, 's%')",
                object.getDescription(), object.cost(), TYPE);

        connection.nonSelectQuery(query);

        query = "SELECT * FROM items ORDER BY id DESC";

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
    public void update(Boisson object) {
        String query = String.format("UPDATE items SET price = %f WHERE id = %d",
                object.cost(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM items WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public Boisson selectOne(int id) {
        String query = String.format("SELECT * FROM items WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Boisson boisson = BoissonFactory.getInstance().makeBoisson(BoissonType.valueOf(resultSet.getString("description")));

                boisson.setId(resultSet.getInt("id"));

                return boisson;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Boisson> selectAll() {
        return filter("type = '" + TYPE + "'");
    }

    @Override
    public List<Boisson> filter(String whereQuery) {

        List<Boisson> boissons = new ArrayList<>();

        String query = "SELECT * FROM items WHERE "+ whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Boisson boisson = BoissonFactory.getInstance().makeBoisson(BoissonType.valueOf(resultSet.getString("description")));

                boisson.setId(resultSet.getInt("id"));

                boissons.add(boisson);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boissons;
    }
}
