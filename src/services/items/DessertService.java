package services.items;

import models.desserts.Dessert;
import models.desserts.PancakeFactory;
import models.desserts.PancakeType;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DessertService implements Service<Dessert> {

    private static final String TYPE = "Dessert";
    private DatabaseConnection connection;
    private static DessertService instance;

    private DessertService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static DessertService getInstance() {
        if (instance == null)
            instance = new DessertService();

        return instance;
    }

    @Override
    public Dessert create(Dessert object) {
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
    public void update(Dessert object) {
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
    public Dessert selectOne(int id) {
        String query = String.format("SELECT * FROM items WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Dessert dessert = null;

                if (resultSet.getString("description").contains("Circulaire"))
                    dessert = PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE);
                else
                    dessert = PancakeFactory.getInstance().makePancake(PancakeType.CARRE);

                dessert.setId(resultSet.getInt("id"));

                return dessert;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Dessert> selectAll() {
        return filter("type = '" + TYPE + "'");
    }

    @Override
    public List<Dessert> filter(String whereQuery) {

        List<Dessert> desserts = new ArrayList<>();

        String query = "SELECT * FROM items WHERE "+ whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Dessert dessert = null;

                if (resultSet.getString("description").contains("Circulaire"))
                    dessert = PancakeFactory.getInstance().makePancake(PancakeType.CIRCULAIRE);
                else
                    dessert = PancakeFactory.getInstance().makePancake(PancakeType.CARRE);

                dessert.setId(resultSet.getInt("id"));

                desserts.add(dessert);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return desserts;
    }

}
