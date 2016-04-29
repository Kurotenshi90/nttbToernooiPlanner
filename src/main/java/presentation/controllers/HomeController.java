package presentation.controllers;

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

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 29/04/2016.
 */
public class HomeController implements Initializable{
    @FXML private TableView<Toernooi> TableViewToernooiOverzicht;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Toernooinaam;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Toernooileider;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Inschrijfdatum;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Begindatum;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Einddatum;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Plaats;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Straat;
    @FXML private TableColumn<Toernooi, String> TableViewToernooiOverzicht_Nummer;
    @FXML private Button Button_ToernooiAanmaken;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        TableViewToernooiOverzicht_Toernooinaam.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        TableViewToernooiOverzicht_Toernooileider.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("leider"));
        TableViewToernooiOverzicht_Inschrijfdatum.setCellValueFactory( Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(Toernooi.getValue().einddatum));
            return property;
        });
        TableViewToernooiOverzicht_Begindatum.setCellValueFactory( Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(Toernooi.getValue().begindatum));
            return property;
        });
        TableViewToernooiOverzicht_Einddatum.setCellValueFactory(Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(format.format(Toernooi.getValue().inschrijfdatum));
            return property;
        });
        TableViewToernooiOverzicht_Plaats.setCellValueFactory(Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(Toernooi.getValue().getLocatie().getPlaats());
            return property;
        });
        TableViewToernooiOverzicht_Straat.setCellValueFactory(Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(Toernooi.getValue().getLocatie().getStraatnaam());
            return property;
        });
        TableViewToernooiOverzicht_Nummer.setCellValueFactory(Toernooi -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(Toernooi.getValue().getLocatie().getHuisnummer());
            return property;
        });
        TableViewToernooiOverzicht.getItems().setAll(Main.storage);
    }
}
