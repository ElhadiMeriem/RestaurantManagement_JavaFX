package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import models.commands.Facture;
import services.commands.FactureService;
import util.FactureView;
import util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FactureController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    TableView tableFactures;
    @FXML
    TableColumn colId;
    @FXML
    TableColumn colIdCommand;
    @FXML
    TableColumn colTotal;
    private ObservableList<FactureView> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<FactureView, Integer>("id"));
        colIdCommand.setCellValueFactory(new PropertyValueFactory<FactureView, Integer>("commmandId"));
        colTotal.setCellValueFactory(new PropertyValueFactory<FactureView, Double>("total"));

        List<Facture> factures = FactureService.getInstance().selectAll();
        for (Facture f : factures) {
            if (f.getCommand().getClient().getId() == Session.getConnectedClient().getId()) {
                FactureView row = new FactureView(new SimpleIntegerProperty(f.getId()),
                                                    new SimpleIntegerProperty(f.getCommand().getId()),
                                                    new SimpleDoubleProperty(f.getTotal()));

                data.add(row);
            }
        }

        tableFactures.setItems(data);
    }

    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
