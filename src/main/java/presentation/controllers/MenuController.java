package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 12/05/2016.
 */
public class MenuController implements Initializable {
    @FXML Button ToernooiBeheren;
    @FXML Button InschrijvenToernooi;
    @FXML Button Inloggen;
    @FXML Button Exit;
    @FXML Button Home;
    @FXML Button ToernooiInplannen;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
    }

    private void initializeButtons() {
        ToernooiBeheren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Home.fxml"));
                HomeController controller = new HomeController();
                loader.setController(controller);
                Stage stage = (Stage) ToernooiBeheren.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });
        InschrijvenToernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/InschrijfToernooi.fxml"));
                ToernooiOverzichtDeelnemerToevoegenController controller = new ToernooiOverzichtDeelnemerToevoegenController();
                loader.setController(controller);
                Stage stage = (Stage) InschrijvenToernooi.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });

        Home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Menu.fxml"));
                HomeController controller = new HomeController();
                loader.setController(controller);
                Stage stage = (Stage) Home.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });

        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ToernooiBeheren.getScene().getWindow();
                stage.close();
            }
        });

        Inloggen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Login.fxml"));
                LoginController controller = new LoginController(getController());
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                Scene scene = new Scene(root, 480, 320);
                stage.setScene(scene);
                stage.initOwner(Inloggen.getScene().getWindow());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            }
        });
        ToernooiInplannen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiInplannen.fxml"));
                DeeltoernooiInplannenController controller = new DeeltoernooiInplannenController();
                loader.setController(controller);
                Stage stage = (Stage) ToernooiInplannen.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });

    }
    private MenuController getController() {
        return this;
    }
}
