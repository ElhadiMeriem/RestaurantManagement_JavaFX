package services.commands;

import models.commands.Command;
import util.DatabaseConnection;
import util.Item;

import java.util.List;

public class CommandDetailsService {

    private DatabaseConnection connection;
    private static CommandDetailsService instance;

    private CommandDetailsService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static CommandDetailsService getInstance() {
        if (instance == null)
            instance = new CommandDetailsService();

        return instance;
    }

    public void create(Command command, List<Item> items) {

        for (Item item : items) {

            String query = String.format("INSERT INTO command_details(id_command, id_item, quantity, price) VALUES(%d, %d, %d, %f)",
                    command.getId(), item.getPlat().getId(), item.getQuantity(), item.getPlat().cost());

            connection.nonSelectQuery(query);

        }
    }

}
