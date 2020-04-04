package services.reservations;

import models.reservations.Table;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableService implements Service<Table> {

    private static TableService instance;
    private DatabaseConnection connection;

    private TableService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static TableService getInstance() {
        if (instance == null)
            instance = new TableService();

        return instance;
    }

    @Override
    public Table create(Table object) {
        String query = String.format("INSERT INTO tables(description) VALUES('%s')",
                object.getDescription());

        connection.nonSelectQuery(query);

        query = "SELECT * FROM tables ORDER BY id DESC";

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
    public void update(Table object) {

        String query = String.format("UPDATE tables SET description = '%s' WHERE id = %d",
                object.getDescription(), object.getId());

        connection.nonSelectQuery(query);

    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM tables WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public Table selectOne(int id) {

        String query = String.format("SELECT * FROM tables WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Table table = new Table(resultSet.getString("description"));

                table.setId(resultSet.getInt("id"));

                return table;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Table> selectAll() {
        return filter("1 = 1");
    }

    @Override
    public List<Table> filter(String whereQuery) {

        List<Table> tables = new ArrayList<>();

        String query = "SELECT * FROM models.users WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Table table = new Table(resultSet.getString("description"));

                table.setId(resultSet.getInt("id"));

                tables.add(table);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tables;
    }
}
