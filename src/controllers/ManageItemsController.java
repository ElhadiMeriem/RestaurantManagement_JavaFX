package controllers;

import javafx.beans.property.SimpleDoubleProperty;
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
import services.items.BoissonService;
import services.items.ItemService;
import util.ItemView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageItemsController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableItems;
    @FXML
    ComboBox comboBoxType;
    @FXML
    TableColumn colId;
    @FXML
    TableColumn colDescription;
    @FXML
    TableColumn colPrice;
    @FXML
    TableColumn colType;
    @FXML
    TextField txtDescription;
    @FXML
    TextField txtPrice;

    private int selectedId = -1;
    private ObservableList<ItemView> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxType.getItems().addAll("Boisson", "Plat d'entree", "Plat Principal");
        comboBoxType.setValue("Boission");

        colId.setCellValueFactory(new PropertyValueFactory<ItemView, Integer>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<ItemView, String>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<ItemView, String>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<ItemView, String>("type"));

        loadItems();
    }

    private void loadItems() {
        data = FXCollections.observableArrayList();

        List<ItemView> items =  ItemService.getInstance().selectAll();

        for (ItemView item : items) {
            data.add(item);
        }

        tableItems.setItems(data);

        tableItems.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ItemView item = (ItemView) newSelection;
                txtDescription.setText(item.getDescription());
                txtPrice.setText(String.valueOf(item.getPrice()));
                comboBoxType.setValue(item.getType());
                selectedId = item.getId();
            }
        });
    }

    public void addAction(ActionEvent actionEvent) {
        ItemView item = new ItemView(new SimpleIntegerProperty(0),
                new SimpleStringProperty(txtDescription.getText()),
                new SimpleDoubleProperty(Double.parseDouble(txtPrice.getText())),
                new SimpleStringProperty(comboBoxType.getValue().toString()));

        ItemService.getInstance().create(item);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item added successfully", ButtonType.OK);
        alert.setTitle("Item added!");
        alert.setHeaderText("Item added!");
        alert.show();

        loadItems();
    }

    public void deleteAction(ActionEvent actionEvent) {
        if (selectedId != -1) {
            BoissonService.getInstance().delete(selectedId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully", ButtonType.OK);
            alert.setTitle("Item deleted!");
            alert.setHeaderText("Item deleted!");
            alert.show();

            loadItems();
        }
    }

    public void updateAction(ActionEvent actionEvent) {
        if (selectedId != -1) {

            ItemView item = new ItemView(new SimpleIntegerProperty(selectedId),
                    new SimpleStringProperty(txtDescription.getText()),
                    new SimpleDoubleProperty(Double.parseDouble(txtPrice.getText())),
                    new SimpleStringProperty(comboBoxType.getValue().toString()));

            ItemService.getInstance().update(item);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item updated successfully", ButtonType.OK);
            alert.setTitle("Item updated!");
            alert.setHeaderText("Item updated!");
            alert.show();

            loadItems();
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
