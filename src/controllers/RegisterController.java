package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.users.Client;
import services.users.ClientService;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signUpAction(ActionEvent actionEvent) {

        List<Client> clients = ClientService.getInstance().filter(String.format("username = '%s' or email = '%s'", txtUsername.getText(), txtEmail.getText()));

        if (clients.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username or Email already exists. please try again!", ButtonType.CLOSE);
            alert.setTitle("Error creating account!");
            alert.setHeaderText("Error creating account!");
            alert.show();
        } else {
            Client client = ClientService.getInstance().create(new Client(txtUsername.getText(), txtFirstName.getText(),
                    txtLastName.getText(), txtEmail.getText(), txtPassword.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello " + client.getFirstName(), ButtonType.OK);
            alert.setTitle("You Are Connected!");
            alert.setHeaderText("You Are Connected!");
            alert.show();

            Session.setConnectedClient(client);

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/MainMenuAdmin.fxml"));
                root.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
