package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import util.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TopBuyingCustomersFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private BarChart barChart;

    @FXML
    private DatePicker txtODate;

    private String date;

    ObservableList<BarChart.Series> barChartData= FXCollections.observableArrayList() ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date="2019-06-03";

        try {
            topCustomers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        barChart.setData(barChartData);
    }

    public void topCustomers()throws ClassNotFoundException,SQLException{
        barChartData.clear();

        String query = "SELECT username, count(c.id) as number_of_orders from commands c, users u where u.id = c.id_client group by u.id order by 2 desc limit 10";

        ResultSet rs = DatabaseConnection.getInstance().selectQuery(query);
        while (rs.next()) {
            BarChart.Series sr = new BarChart.Series<>();
            sr.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            sr.setName(rs.getString(1));
            barChartData.add(sr);
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

    public void menuAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/MainMenuAdmin.fxml"));
            root.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setBarChart(ActionEvent actionEvent){
        date= String.valueOf(txtODate.getValue());
        barChartData.clear();

        String query = "SELECT username, count(c.id) as number_of_orders from commands c, users u where u.id = c.id_client and c.date = '"+date+"' group by u.id order by 2 desc limit 10";

        ResultSet rs = DatabaseConnection.getInstance().selectQuery(query);
        try {
            while (rs.next()) {
                BarChart.Series sr = new BarChart.Series<>();
                sr.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                sr.setName(rs.getString(1));
                barChartData.add(sr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
