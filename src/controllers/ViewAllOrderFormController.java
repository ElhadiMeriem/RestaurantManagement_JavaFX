package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.DatabaseConnection;
import util.OrderDetailView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewAllOrderFormController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableOrders;
    @FXML
    TableColumn colOrderNo;
    @FXML
    TableColumn colCustomerId;
    @FXML
    TableColumn colCustomerName;
    @FXML
    TableColumn colOrderDate;
    @FXML
    TableColumn colLivrable;
    @FXML
    DatePicker txtODate;

    private ObservableList<OrderDetailView> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderNo.setCellValueFactory(new PropertyValueFactory<OrderDetailView, Integer>("id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<OrderDetailView, Integer>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<OrderDetailView, String>("customerName"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<OrderDetailView, String>("orderDate"));
        colLivrable.setCellValueFactory(new PropertyValueFactory<OrderDetailView, String>("livrable"));

        loadAllOrders();
    }

    private void loadAllOrders() {
        data = FXCollections.observableArrayList();

        String query = "SELECT c.id, u.id, u.username, c.date, c.livrable FROM commands c, users u where c.id_client = u.id";

        ResultSet rs =  DatabaseConnection.getInstance().selectQuery(query);

        try {
            while (rs.next()) {
                OrderDetailView row = new OrderDetailView(new SimpleIntegerProperty(rs.getInt(1)), new SimpleIntegerProperty(rs.getInt(2)),
                                        new SimpleStringProperty(rs.getString(3)), new SimpleStringProperty(rs.getString(4)),
                                        new SimpleStringProperty(rs.getInt(5) == 1 ? "Livrable" : "Surplace"));

                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOrders.setItems(data);

    }

    public void loadOrdersByDate(ActionEvent actionEvent) {
        String date = String.valueOf(txtODate.getValue());

        data = FXCollections.observableArrayList();

        String query = "SELECT c.id, u.id, u.username, c.date, c.livrable FROM commands c, users u where c.id_client = u.id and date = '" + date + "'";

        ResultSet rs =  DatabaseConnection.getInstance().selectQuery(query);

        try {
            while (rs.next()) {
                OrderDetailView row = new OrderDetailView(new SimpleIntegerProperty(rs.getInt(1)), new SimpleIntegerProperty(rs.getInt(2)),
                        new SimpleStringProperty(rs.getString(3)), new SimpleStringProperty(rs.getString(4)),
                        new SimpleStringProperty(rs.getInt(5) == 1 ? "Livrable" : "Surplace"));

                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOrders.setItems(data);

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

    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/MainMenuAdmin.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
