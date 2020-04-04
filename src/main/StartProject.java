package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class StartProject extends Application{

    public static boolean isSplashLoaded =false;

    @Override
    public void start(Stage primaryStage)throws Exception{
        Parent root=FXMLLoader.load(getClass().getResource("/views/DashBoard.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome To Our Restaurant");
        primaryStage.show();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}