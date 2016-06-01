package presentation.controllers;

import domain.Deelnemer;
import domain.Tafel;
import domain.Toernooi;
import domain.Util.WedstrijdListCell;
import domain.Wedstrijd;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import presentation.models.WedstrijdenInplannenModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenInplannenController implements Initializable {
    @FXML Button Annuleren;
    @FXML Button VerwijderTafel;
    @FXML Button TafelsToevoegen;
    @FXML TextField Aantal;
    @FXML TableView Tafels;
    @FXML TableColumn<Tafel, String> Tafelnaam;


    @FXML ListView Wedstrijden;


    private WedstrijdenInplannenModel wedstrijdenInplannenModel;
    public WedstrijdenInplannenController(int toernooinummer) {
        wedstrijdenInplannenModel = new WedstrijdenInplannenModel(toernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTafels();
        initializeButtons();
        initializeWedstrijden();
    }

    private void initializeWedstrijden() {

        Wedstrijden.setCellFactory(new Callback<ListView<String>,
                                    ListCell<Wedstrijd>>() {
                                @Override
                                public ListCell<Wedstrijd> call(ListView<String> list) {
                                    return new WedstrijdListCell();
                                }
                            }
        );
    }

    private void initializeTafels(){
        Tafelnaam.setCellValueFactory(new PropertyValueFactory<Tafel, String>("naam"));

        Tafels.getItems().setAll(wedstrijdenInplannenModel.tafels());
    }

    private  void initializeButtons(){
        TafelsToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wedstrijdenInplannenModel.addTafels(Integer.parseInt(Aantal.getText()));
                Tafels.getItems().setAll(wedstrijdenInplannenModel.tafels());
            }
        });
    }
}
