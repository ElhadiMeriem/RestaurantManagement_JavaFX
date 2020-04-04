package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.users.Administrateur;
import models.users.Caissier;
import models.users.Client;
import models.users.User;
import services.users.AdministrateurService;
import services.users.CaissierService;
import services.users.ClientService;
import services.users.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableUsers;
    @FXML
    ComboBox comboBoxRoles;
    @FXML
    TableColumn colId;
    @FXML
    TableColumn colFirstName;
    @FXML
    TableColumn colLastName;
    @FXML
    TableColumn colUsername;
    @FXML
    TableColumn colPassword;
    @FXML
    TableColumn colRole;
    @FXML
    TableColumn colEmail;
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;

    private ObservableList<util.User> data = FXCollections.observableArrayList();
    private int selectedId = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<util.User, Integer>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<util.User, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<util.User, String>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<util.User, String>("email"));
        colUsername.setCellValueFactory(new PropertyValueFactory<util.User, String>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<util.User, String>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<util.User, String>("password"));

        comboBoxRoles.getItems().addAll("client", "caissier", "admin");
        comboBoxRoles.setValue("client");

        loadUsers();

    }

    private void loadUsers() {
        data = FXCollections.observableArrayList();

        List<User> users =  UserService.getInstance().selectAll();
        for (User user : users) {
            util.User row = new util.User(new SimpleIntegerProperty(user.getId()), new SimpleStringProperty(user.getFirstName()),
                    new SimpleStringProperty(user.getLastName()), new SimpleStringProperty(user.getUsername()),
                    new SimpleStringProperty(user.getEmail()), new SimpleStringProperty(user.getPassword()), new SimpleStringProperty("client"));
            if (user instanceof Caissier)
                row.setRole("caissier");
            else if (user instanceof Administrateur)
                row.setRole("admin");

            data.add(row);
        }

        tableUsers.setItems(data);

        tableUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                util.User user = (util.User)newSelection;
                txtFirstName.setText(user.getFirstName());
                txtLastName.setText(user.getLastName());
                txtUsername.setText(user.getUsername());
                txtEmail.setText(user.getEmail());
                txtPassword.setText(user.getPassword());
                comboBoxRoles.setValue(user.getRole());
                selectedId = user.getId();
            }
        });
    }

    public void addAction(ActionEvent actionEvent) {
        String role = comboBoxRoles.getValue().toString();

        List<Client> clients = ClientService.getInstance().filter(String.format("username = '%s' or email = '%s'", txtUsername.getText(), txtEmail.getText()));

        if (clients.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username or Email already exists. please try again!", ButtonType.CLOSE);
            alert.setTitle("Error creating user!");
            alert.setHeaderText("Error creating user!");
            alert.show();
        } else {

            if (role.equals("Client"))
                ClientService.getInstance().create(new Client(txtUsername.getText(), txtFirstName.getText(),
                        txtLastName.getText(), txtEmail.getText(), txtPassword.getText()));
            else if (role.equals("Admin"))
                AdministrateurService.getInstance().create(new Administrateur(txtUsername.getText(), txtFirstName.getText(),
                        txtLastName.getText(), txtEmail.getText(), txtPassword.getText()));
            else
                CaissierService.getInstance().create(new Caissier(txtUsername.getText(), txtFirstName.getText(),
                        txtLastName.getText(), txtEmail.getText(), txtPassword.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User added successfully", ButtonType.OK);
            alert.setTitle("User added!");
            alert.setHeaderText("User added!");
            alert.show();

            loadUsers();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        if (selectedId != -1) {
            ClientService.getInstance().delete(selectedId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User deleted successfully", ButtonType.OK);
            alert.setTitle("User deleted!");
            alert.setHeaderText("User deleted!");
            alert.show();

            loadUsers();
        }
    }

    public void updateAction(ActionEvent actionEvent) {
        if (selectedId != -1) {
            Client user = new Client(txtUsername.getText(), txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtPassword.getText());
            user.setId(selectedId);
            ClientService.getInstance().update(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "User updated successfully", ButtonType.OK);
            alert.setTitle("User updated!");
            alert.setHeaderText("User updated!");
            alert.show();

            loadUsers();
        }
    }

    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/MainMenuAdmin.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
