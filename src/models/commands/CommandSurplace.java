package models.commands;

import models.reservations.Table;

import java.util.Date;

public class CommandSurplace extends Command {

    private Table table;

    public CommandSurplace(Date date) {
        super(date);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}
