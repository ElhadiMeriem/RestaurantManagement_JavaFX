package util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReservationView {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty client;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleIntegerProperty nbPersons;
    private final SimpleIntegerProperty idTable;

    public ReservationView(SimpleIntegerProperty id, SimpleStringProperty client, SimpleStringProperty date, SimpleStringProperty time, SimpleIntegerProperty nbPersons, SimpleIntegerProperty idTable) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.time = time;
        this.nbPersons = nbPersons;
        this.idTable = idTable;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getClient() {
        return client.get();
    }

    public SimpleStringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getNbPersons() {
        return nbPersons.get();
    }

    public SimpleIntegerProperty nbPersonsProperty() {
        return nbPersons;
    }

    public void setNbPersons(int nbPersons) {
        this.nbPersons.set(nbPersons);
    }

    public int getIdTable() {
        return idTable.get();
    }

    public SimpleIntegerProperty idTableProperty() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable.set(idTable);
    }
}
