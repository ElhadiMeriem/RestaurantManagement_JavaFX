package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void makeReservationAction(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/ReserverTable.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutAction(MouseEvent mouseEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }
}
