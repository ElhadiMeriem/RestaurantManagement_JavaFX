package services.items;

import models.plats.principal.FrenchFactory;
import models.plats.principal.ItalianFactory;
import models.plats.principal.PizzaType;
import models.plats.principal.PlatPrincipal;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatPrincipalService implements Service<PlatPrincipal> {

    private static final String TYPE = "Plat principal";
    private DatabaseConnection connection;
    private static PlatPrincipalService instance;

    private PlatPrincipalService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static PlatPrincipalService getInstance() {
        if (instance == null)
            instance = new PlatPrincipalService();

        return instance;
    }

    @Override
    public PlatPrincipal create(PlatPrincipal object) {
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
    public void update(PlatPrincipal object) {
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
    public PlatPrincipal selectOne(int id) {
        String query = String.format("SELECT * FROM items WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                PizzaType type = PizzaType.valueOf(resultSet.getString("description"));

                PlatPrincipal platEnt = null;

                if (type == PizzaType.CHEESE || type == PizzaType.FRUIT_DE_MER)
                    platEnt = FrenchFactory.getInstance().createPizza(PizzaType.valueOf(resultSet.getString("description")));
                else
                    platEnt = ItalianFactory.getInstance().createPizza(PizzaType.valueOf(resultSet.getString("description")));

                platEnt.setId(resultSet.getInt("id"));

                return platEnt;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<PlatPrincipal> selectAll() {
        return filter("type = '" + TYPE + "'");
    }

    @Override
    public List<PlatPrincipal> filter(String whereQuery) {

        List<PlatPrincipal> platEnts = new ArrayList<>();

        String query = "SELECT * FROM items WHERE "+ whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                PizzaType type = PizzaType.valueOf(resultSet.getString("description"));

                PlatPrincipal platEnt = null;

                if (type == PizzaType.CHEESE || type == PizzaType.FRUIT_DE_MER)
                    platEnt = FrenchFactory.getInstance().createPizza(PizzaType.valueOf(resultSet.getString("description")));
                else
                    platEnt = ItalianFactory.getInstance().createPizza(PizzaType.valueOf(resultSet.getString("description")));

                platEnt.setId(resultSet.getInt("id"));

                platEnts.add(platEnt);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return platEnts;
    }

}
