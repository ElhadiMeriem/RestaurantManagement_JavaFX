package services.commands;

import models.commands.Facture;
import services.Service;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FactureService implements Service<Facture> {

    private DatabaseConnection connection;
    private static FactureService instance;

    private FactureService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static FactureService getInstance() {
        if (instance == null)
            instance = new FactureService();

        return instance;
    }

    @Override
    public Facture create(Facture object) {

        String query = String.format("INSERT INTO factures(id_command, total) VALUES(%d, %f)",
                object.getCommand().getId(), object.getTotal());

        connection.nonSelectQuery(query);

        query = "SELECT * FROM factures ORDER BY id DESC";

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
    public void update(Facture object) {
        String query = String.format("UPDATE factures SET id_command = %d, total = %f WHERE id = %d",
                object.getCommand().getId(), object.getTotal(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM factures WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public Facture selectOne(int id) {
        String query = String.format("SELECT * FROM factures WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Facture facture = new Facture(resultSet.getDouble("total"));

                facture.setId(resultSet.getInt("id"));
                facture.setCommand(CommandSurplaceService.getInstance().selectOne(resultSet.getInt("id_command")));

                return facture;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Facture> selectAll() {
        return filter("1 = 1");
    }

    @Override
    public List<Facture> filter(String whereQuery) {
        List<Facture> factures = new ArrayList<>();

        String query = "SELECT * FROM factures WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Facture facture = new Facture(resultSet.getDouble("total"));

                facture.setId(resultSet.getInt("id"));
                facture.setCommand(CommandSurplaceService.getInstance().selectOne(resultSet.getInt("id_command")));

                factures.add(facture);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return factures;
    }

}
