package services.items;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import services.Service;
import util.DatabaseConnection;
import util.ItemView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements Service<ItemView> {

    private DatabaseConnection connection;
    private static ItemService instance;

    private ItemService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static ItemService getInstance() {
        if (instance == null)
            instance = new ItemService();

        return instance;
    }

    @Override
    public ItemView create(ItemView object) {

        String query = String.format("INSERT INTO items(description, type, price) VALUES ('%s','%s',%f)",
                object.getDescription(), object.getType(), object.getPrice());

        connection.nonSelectQuery(query);

        return null;
    }

    @Override
    public void update(ItemView object) {
        String query = String.format("UPDATE items SET description = '%s', type = '%s', price = %f WHERE id = %d",
                object.getDescription(), object.getType(), object.getPrice(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ItemView selectOne(int id) {
        return null;
    }

    @Override
    public List<ItemView> selectAll() {
        return filter("1 = 1");
    }

    @Override
    public List<ItemView> filter(String whereQuery) {
        List<ItemView> items = new ArrayList<>();

        String query = "SELECT * FROM items WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                ItemView item = new ItemView(new SimpleIntegerProperty(resultSet.getInt("id")),
                        new SimpleStringProperty(resultSet.getString("description")),
                        new SimpleDoubleProperty(resultSet.getDouble("price")),
                        new SimpleStringProperty(resultSet.getString("type")));

                items.add(item);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return items;
    }
}
