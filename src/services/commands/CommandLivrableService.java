package services.commands;

import models.commands.CommandLivrable;
import services.Service;
import services.users.ClientService;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandLivrableService implements Service<CommandLivrable> {

    private DatabaseConnection connection;
    private static CommandLivrableService instance;

    private CommandLivrableService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static CommandLivrableService getInstance() {
        if (instance == null)
            instance = new CommandLivrableService();

        return instance;
    }

    @Override
    public CommandLivrable create(CommandLivrable object) {

        String query = String.format("INSERT INTO models.commands(id_client, date, livrable) VALUES(%d, '%s', %d)",
                object.getClient().getId(), object.getDate().toString(), 1);

        connection.nonSelectQuery(query);

        query = "SELECT * FROM models.commands ORDER BY id DESC";

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
    public void update(CommandLivrable object) {
        String query = String.format("UPDATE models.commands SET id_client = %d, date = '%s' WHERE id = %d",
                object.getClient().getId(), object.getDate().toString(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM models.commands WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public CommandLivrable selectOne(int id) {
        String query = String.format("SELECT * FROM models.commands WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                CommandLivrable commandLivrable = new CommandLivrable(resultSet.getDate("date"), "");

                commandLivrable.setId(resultSet.getInt("id"));
                commandLivrable.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));

                return commandLivrable;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<CommandLivrable> selectAll() {
        return filter("livrable = 1");
    }

    @Override
    public List<CommandLivrable> filter(String whereQuery) {
        List<CommandLivrable> commandLivrables = new ArrayList<>();

        String query = "SELECT * FROM models.commands WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                CommandLivrable commandLivrable = new CommandLivrable(resultSet.getDate("date"), "");

                commandLivrable.setId(resultSet.getInt("id"));
                commandLivrable.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));

                commandLivrables.add(commandLivrable);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return commandLivrables;
    }
}
