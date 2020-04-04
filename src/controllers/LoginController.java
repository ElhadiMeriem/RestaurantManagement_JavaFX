package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.users.User;
import services.users.AdministrateurService;
import services.users.CaissierService;
import services.users.ClientService;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    Button btnLogin;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassword;
    @FXML
    AnchorPane root;
    @FXML
    ComboBox comboRole;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboRole.getItems().addAll("client", "admin", "caissier");
        comboRole.setValue("client");
    }

    @FXML
    public void loginAction(ActionEvent actionEvent) {
        List<User> users = new ArrayList<>();

        if (comboRole.getValue().equals("client")) {
            users.addAll(ClientService.getInstance().filter(String.format("email = '%s' and password = '%s'", txtEmail.getText(), txtPassword.getText())));
        } else if (comboRole.getValue().equals("admin")) {
            users.addAll(AdministrateurService.getInstance().filter(String.format("email = '%s' and password = '%s'", txtEmail.getText(), txtPassword.getText())));
        } else {
            users.addAll(CaissierService.getInstance().filter(String.format("email = '%s' and password = '%s'", txtEmail.getText(), txtPassword.getText())));
        }

        if (users.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello " + users.get(0).getFirstName(), ButtonType.OK);
            alert.setTitle("You Are Connected!");
            alert.setHeaderText("You Are Connected!");
            alert.show();
            Session.setConnectedClient(users.get(0));

            AnchorPane pane;
            try {
                if (comboRole.getValue().equals("admin")) {
                    pane = FXMLLoader.load(getClass().getResource("/views/MainMenuAdmin.fxml"));
                } else if (comboRole.getValue().equals("caissier")) {
                    pane = FXMLLoader.load(getClass().getResource("/views/ViewAllReservation.fxml"));
                } else {
                    pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
                }
                root.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Incorrect Email or Password. please try again!", ButtonType.CLOSE).show();
        }

    }

    public void signUpAction(ActionEvent actionEvent) {
        try {
            AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/views/Register.fxml")));
            root.getChildren().setAll(parentContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
