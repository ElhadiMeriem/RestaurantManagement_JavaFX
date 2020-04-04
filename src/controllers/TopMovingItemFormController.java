
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


 
public class TopMovingItemFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblItemDetails;
    
    @FXML
    private PieChart pchaPizza;

    @FXML
    DatePicker txtODate;

    private String date;

    ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date="2017-05-31";
        try {
            chartPizza();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pchaPizza.setData(pieChartData);
    }  
    
    public void chartPizza() throws SQLException, ClassNotFoundException {
        pieChartData.clear();

        String query = "SELECT description, count(c.id) as number_of_orders from items i, command_details c where i.id = c.id_item group by i.id order by 2 desc limit 10";

        ResultSet rs = DatabaseConnection.getInstance().selectQuery(query);
        try {
            while (rs.next()) {
                pieChartData.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pchaPizza.setData(pieChartData);
    }
    
    @FXML
    private void btnLogOut(ActionEvent actionEvent){
        try {
            AnchorPane loginForm = FXMLLoader.load(getClass().getResource(("/view/fxml/LoginForm.fxml")));
            root.getChildren().setAll(loginForm);
        }catch(IOException e){
            System.out.println(e);
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

    @FXML
    private void itemDetailsEvent(MouseEvent event) {
        pchaPizza.getData().stream()
                .forEach(data -> {data.getNode().addEventHandler(MouseEvent.ANY,event1 -> {
                    lblItemDetails
                            .setText(
                                    data.getName()+" ("+
                                            (int)data.getPieValue()+")"+"\nPercentage : "+
                                            Double.parseDouble(String.valueOf(((
                                                    data.getPieValue()/100)*360)))+"%");
                });
        });
    }


    @FXML
    private void chartChanging(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        date= String.valueOf(txtODate.getValue());

        pieChartData.clear();

        String query = "SELECT description, count(c.id) as number_of_orders from items i, command_details cd, commands c where i.id = cd.id_item and cd.id_command = c.id and c.date = '"+date+"' group by i.id order by 2 desc limit 10";

        ResultSet rs = DatabaseConnection.getInstance().selectQuery(query);
        try {
            while (rs.next()) {
                pieChartData.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pchaPizza.setData(pieChartData);
    }
    
}
