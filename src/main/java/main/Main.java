package main;

/**
 * Created by Peter-Paul on 29/04/2016.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.controllers.MenuController;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Menu.fxml"));
            MenuController controller = new MenuController();
            loader.setController(controller);
            Parent root = loader.load();
            primaryStage.setTitle("Toernooi manager V0.0.0.1");
            primaryStage.setScene(new Scene(root, 1920, 1080));
            primaryStage.setMaximized(true);
            primaryStage.setResizable(false);
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

