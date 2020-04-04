package services.reservations;

import models.reservations.Reservation;
import services.Service;
import services.users.ClientService;
import util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements Service<Reservation> {

    private static ReservationService instance;
    private DatabaseConnection connection;

    private ReservationService() {
        this.connection = DatabaseConnection.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null)
            instance = new ReservationService();

        return instance;
    }

    @Override
    public Reservation create(Reservation object) {

        String query = String.format("INSERT INTO reservations(time, number, date, id_client, id_table) VALUES('%s', %d, '%s', %d, %d)",
                object.getTime().format(DateTimeFormatter.ISO_TIME), object.getNumberOfPersons(), object.getTime().format(DateTimeFormatter.ISO_DATE),
                object.getClient().getId(), object.getTable().getId());

        connection.nonSelectQuery(query);

        query = "SELECT * FROM reservations ORDER BY id DESC";

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
    public void update(Reservation object) {
        String query = String.format("UPDATE reservations SET date = '%s', time = '%s', number = %d, id_client = %d, id_table = %d WHERE id = %d",
                object.getTime().format(DateTimeFormatter.ISO_DATE), object.getTime().format(DateTimeFormatter.ISO_TIME),
                object.getNumberOfPersons(), object.getClient().getId(), object.getTable().getId(), object.getId());

        connection.nonSelectQuery(query);
    }

    @Override
    public void delete(int id) {
        String query = String.format("DELETE FROM reservations WHERE id = %d", id);

        connection.nonSelectQuery(query);
    }

    @Override
    public Reservation selectOne(int id) {

        String query = String.format("SELECT * FROM reservations WHERE id = %d", id);

        ResultSet resultSet = connection.selectQuery(query);

        try {
            if (resultSet.next()) {

                Reservation reservation = new Reservation(LocalDateTime.of(resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("time").toLocalTime()), resultSet.getInt("number"));

                reservation.setId(resultSet.getInt("id"));
                reservation.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));
                reservation.setTable(TableService.getInstance().selectOne(resultSet.getInt("id_table")));

                return reservation;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reservation> selectAll() {
        return filter("1 = 1");
    }

    @Override
    public List<Reservation> filter(String whereQuery) {

        List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT * FROM reservations WHERE " + whereQuery;

        ResultSet resultSet = connection.selectQuery(query);

        try {
            while (resultSet.next()) {

                Reservation reservation = new Reservation(LocalDateTime.of(resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("time").toLocalTime()), resultSet.getInt("number"));

                reservation.setId(resultSet.getInt("id"));
                reservation.setClient(ClientService.getInstance().selectOne(resultSet.getInt("id_client")));
                reservation.setTable(TableService.getInstance().selectOne(resultSet.getInt("id_table")));

                reservations.add(reservation);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return reservations;
    }
}
