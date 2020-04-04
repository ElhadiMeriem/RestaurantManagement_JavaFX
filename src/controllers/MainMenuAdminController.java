package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuAdminController implements Initializable {

    @FXML
    AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void manageUsersAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/ManageUsers.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
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

    public void manageItems(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/ManageItems.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }

    public void summaryAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/views/SummaryMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().setAll(pane);
    }
}
