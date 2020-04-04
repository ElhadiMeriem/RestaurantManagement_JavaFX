package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.reservations.Reservation;
import models.users.Client;
import services.reservations.ReservationService;
import services.reservations.TableService;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReserverTableController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    DatePicker txtDate;
    @FXML
    TextField txtNbPersons;
    @FXML
    TextField txtTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void findTable(ActionEvent actionEvent) {
        try {
            Reservation r = new Reservation(LocalDateTime.of(txtDate.getValue(), LocalTime.parse(txtTime.getText())), Integer.valueOf(txtNbPersons.getText()));
            r.setTable(TableService.getInstance().selectOne(1));
            r.setClient((Client) Session.getConnectedClient());
            ReservationService.getInstance().create(r);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your table has been reserved successfully", ButtonType.OK);
            alert.setTitle("Your table has been reserved successfully!");
            alert.setHeaderText("Welcome to our restaurant, enjoy your time!");
            alert.show();

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
                root.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            e.printStackTrace();
        }
    }

    public void returnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
