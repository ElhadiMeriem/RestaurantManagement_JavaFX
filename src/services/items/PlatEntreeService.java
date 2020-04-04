package services.items;

import models.plats.entree.PlatEnt;
import models.plats.entree.SaladFactory;
import models.plats.entree.SaladType;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatEntreeService implements Service<PlatEnt> {

    private static final String TYPE = "Plat d'entree";
    private DatabaseConnection connection;
    private static PlatEntreeService instance;

    private PlatEntreeService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static PlatEntreeService getInstance() {
        if (instance == null)
            instance = new PlatEntreeService();

        return instance;
    }

    @Override
    public PlatEnt create(PlatEnt object) {
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
    public void update(PlatEnt object) {
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
    public PlatEnt selectOne(int id) {
        String query = String.format("SELECT * FROM items WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                PlatEnt platEnt = SaladFactory.getInstance().makeSalad(SaladType.valueOf(resultSet.getString("description")));

                platEnt.setId(resultSet.getInt("id"));

                return platEnt;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<PlatEnt> selectAll() {
        return filter("type = '" + TYPE + "'");
    }

    @Override
    public List<PlatEnt> filter(String whereQuery) {

        List<PlatEnt> platEnts = new ArrayList<>();

        String query = "SELECT * FROM items WHERE "+ whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                PlatEnt platEnt = SaladFactory.getInstance().makeSalad(SaladType.valueOf(resultSet.getString("description")));

                platEnt.setId(resultSet.getInt("id"));

                platEnts.add(platEnt);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return platEnts;
    }

}
