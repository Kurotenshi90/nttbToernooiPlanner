package presentation.controllers;

import domain.HomePageToernooi;
import domain.Toernooi;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import presentation.models.HomePageModel;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 29/04/2016.
 */
public class HomeController implements Initializable{
    @FXML private TableView<HomePageToernooi> TableViewToernooiOverzicht;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Toernooinaam;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Toernooileider;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Inschrijfdatum;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Begindatum;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Einddatum;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Plaats;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Straat;
    @FXML private TableColumn<HomePageToernooi, String> TableViewToernooiOverzicht_Nummer;
    @FXML private Button Button_ToernooiAanmaken;
    private HomePageModel homePageModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePageModel = new HomePageModel();

        initializeTableViewToernooiOverzicht();
        initializeButtons();

    }

    private void initializeButtons() {
        Button_ToernooiAanmaken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) Button_ToernooiAanmaken.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("views/NieuwToernooi.fxml"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    private void initializeTableViewToernooiOverzicht(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        TableViewToernooiOverzicht_Toernooinaam.setCellValueFactory(new PropertyValueFactory<HomePageToernooi, String>("toernooinaam"));
        TableViewToernooiOverzicht_Toernooileider.setCellValueFactory(new PropertyValueFactory<HomePageToernooi, String>("toernooileider"));
        TableViewToernooiOverzicht_Inschrijfdatum.setCellValueFactory( HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(HomePageToernooi.getValue().getEinddatum()));
            return property;
        });
        TableViewToernooiOverzicht_Begindatum.setCellValueFactory( HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(HomePageToernooi.getValue().getBegindatum()));
            return property;
        });
        TableViewToernooiOverzicht_Einddatum.setCellValueFactory(HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(HomePageToernooi.getValue().getInschrijdatum()));
            return property;
        });
        TableViewToernooiOverzicht_Plaats.setCellValueFactory(HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(HomePageToernooi.getValue().getPlaats());
            return property;
        });
        TableViewToernooiOverzicht_Straat.setCellValueFactory(HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(HomePageToernooi.getValue().getStraat());
            return property;
        });
        TableViewToernooiOverzicht_Nummer.setCellValueFactory(HomePageToernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(HomePageToernooi.getValue().getNummer());
            return property;
        });
        TableViewToernooiOverzicht.getItems().setAll(homePageModel.getHomepagetoernooi());
    }
}
