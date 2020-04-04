package models.reservations;

import models.users.Client;

import java.time.LocalDateTime;

public class Reservation {

    private int id;
    private Client client;
    private Table table;
    private LocalDateTime time;
    private int numberOfPersons;

    public Reservation(LocalDateTime time, int numberOfPersons) {
        this.time = time;
        this.numberOfPersons = numberOfPersons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

}




