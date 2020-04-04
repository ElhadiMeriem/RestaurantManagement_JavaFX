package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.reservations.Reservation;
import services.reservations.ReservationService;
import util.ReservationView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAllReservationController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableReservations;
    @FXML
    TableColumn colId;
    @FXML
    TableColumn colClient;
    @FXML
    TableColumn colDate;
    @FXML
    TableColumn colTime;
    @FXML
    TableColumn colNbPersons;
    @FXML
    TableColumn colIdTable;

    private ObservableList<ReservationView> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<ReservationView, Integer>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("client"));
        colDate.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("time"));
        colNbPersons.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("nbPersons"));
        colIdTable.setCellValueFactory(new PropertyValueFactory<ReservationView, String>("idTable"));

        loadAllReservations();
    }

    private void loadAllReservations() {
        data = FXCollections.observableArrayList();
        List<Reservation> reservations =  ReservationService.getInstance().selectAll();

        for (Reservation r : reservations) {
            ReservationView row = new ReservationView(new SimpleIntegerProperty(r.getId()), new SimpleStringProperty(r.getClient().getUsername()),
                                        new SimpleStringProperty(r.getTime().toLocalDate().toString()),
                                        new SimpleStringProperty(r.getTime().toLocalTime().toString()),
                                        new SimpleIntegerProperty(r.getNumberOfPersons()),
                                        new SimpleIntegerProperty(r.getTable().getId()));

            data.add(row);
        }

        tableReservations.setItems(data);
    }

    public void logOutAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }
}
