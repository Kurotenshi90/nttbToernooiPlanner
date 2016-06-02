package presentation.controllers;

import domain.*;
import domain.Util.WedstrijdListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.models.WedstrijdenInplannenModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenInplannenController implements Initializable {
    private int toernooinr;
    @FXML Button Annuleren;
    @FXML Button TafelsToevoegen;
    @FXML TextField Aantal;
    @FXML ListView<Tafel> Tafels;

    @FXML Button AddTafel;
    @FXML Button DeleteTafel;


    @FXML ListView Wedstrijden;
    @FXML ListView IngeplandeWedstrijden;

    @FXML ChoiceBox<Deeltoernooi> SelecteerDeeltoernooi;


    private WedstrijdenInplannenModel wedstrijdenInplannenModel;
    public WedstrijdenInplannenController(int toernooinummer) {
        toernooinr = toernooinummer;
        wedstrijdenInplannenModel = new WedstrijdenInplannenModel(toernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTafels();
        initializeButtons();
        initializeWedstrijden();
        initializeGeplandeWedstrijden();
        initializeSelecteerDeeltoernooi();
    }

    private void initializeSelecteerDeeltoernooi() {
        SelecteerDeeltoernooi.getItems().setAll(wedstrijdenInplannenModel.getDeeltoernoois());
    }

    private void initializeGeplandeWedstrijden() {
        IngeplandeWedstrijden.setCellFactory(new Callback<ListView<String>,
                                           ListCell<Wedstrijd>>() {
                                       @Override
                                       public ListCell<Wedstrijd> call(ListView<String> list) {
                                           return new WedstrijdListCell();
                                       }
                                   }
        );
        IngeplandeWedstrijden.getItems().setAll(wedstrijdenInplannenModel.getGeplandeWedstrijden());
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

        Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijden());
    }

    private void initializeTafels(){
        Tafels.getItems().setAll(wedstrijdenInplannenModel.getPlanningWedstrijd().getTafels());
    }

    private  void initializeButtons(){
        TafelsToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wedstrijdenInplannenModel.addTafels(Integer.parseInt(Aantal.getText()));
                wedstrijdenInplannenModel.getPlanningwedstrijd(toernooinr);
                Tafels.getItems().setAll(wedstrijdenInplannenModel.getPlanningWedstrijd().getTafels());
                IngeplandeWedstrijden.getItems().setAll(wedstrijdenInplannenModel.getGeplandeWedstrijden());
                Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijden());
            }
        });

        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/InschrijfToernooi.fxml"));
                SelecteerToernooiInplannen controller = new SelecteerToernooiInplannen();
                loader.setController(controller);
                Stage stage = (Stage) Annuleren.getScene().getWindow();
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

        AddTafel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wedstrijd wedstrijd = ((Wedstrijd) Wedstrijden.getSelectionModel().getSelectedItem());
                Tafel tafel = Tafels.getSelectionModel().getSelectedItem();
                if(wedstrijd!=null && tafel!=null){
                    wedstrijdenInplannenModel.koppelWedstrijd(wedstrijd, tafel);
                    IngeplandeWedstrijden.getItems().setAll(wedstrijdenInplannenModel.getGeplandeWedstrijden());
                    Tafels.getItems().setAll(wedstrijdenInplannenModel.getPlanningWedstrijd().getTafels());
                    Deeltoernooi deeltoernooi = SelecteerDeeltoernooi.getSelectionModel().getSelectedItem();
                    if(deeltoernooi!=null){
                        Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijdenOnDeeltoernooir(deeltoernooi.getDeeltoernooinr()));
                    } else {
                        Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijden());
                    }

                }
            }
        });

        DeleteTafel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wedstrijd wedstrijd = ((Wedstrijd) IngeplandeWedstrijden.getSelectionModel().getSelectedItem());
                if(wedstrijd!=null){
                    wedstrijdenInplannenModel.onKoppelWedstrijd(wedstrijd);
                    Deeltoernooi deeltoernooi = SelecteerDeeltoernooi.getSelectionModel().getSelectedItem();
                    if(deeltoernooi!=null){
                        Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijdenOnDeeltoernooir(deeltoernooi.getDeeltoernooinr()));
                    } else {
                        IngeplandeWedstrijden.getItems().setAll(wedstrijdenInplannenModel.getGeplandeWedstrijden());
                    }
                    Tafels.getItems().setAll(wedstrijdenInplannenModel.getPlanningWedstrijd().getTafels());
                    IngeplandeWedstrijden.getItems().setAll(wedstrijdenInplannenModel.getGeplandeWedstrijden());

                }
            }
        });

        SelecteerDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wedstrijden.getItems().setAll(wedstrijdenInplannenModel.getNietGeplandeWedstrijdenOnDeeltoernooir(SelecteerDeeltoernooi.getSelectionModel().getSelectedItem().getDeeltoernooinr()));
            }
        });
    }
}
