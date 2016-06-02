package presentation.controllers;

import domain.*;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.PouleInplannenModel;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by dirk on 26-5-2016.
 */
public class PouleInplannenController implements Initializable {
    private PouleInplannenModel pouleInplannenModel;

    @FXML private TableView<Deelnemer> Deelnemers;
    @FXML private TableColumn<Deelnemer, String> DeelnemersBondsnummer;
    @FXML private TableColumn<Deelnemer, String> DeelnemersVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersAchternaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersLicentie;

    @FXML private TableView<Poule> Poules;
    @FXML private TableColumn<Poule, String> PoulesPoulenummer;

    @FXML private TableView<Deelnemer> DeelnemersInPoule;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInPouleBondsnummer;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInPouleVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInPouleAchternaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInPouleLicentie;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInPoulePoulenummer;

    @FXML private TableView<Toernooi> ToernooiInfoTable;
    @FXML private TableColumn<Toernooi, String> ToernooiNaamInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiStartdatumInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiEinddatumInfo;


    @FXML private TextField TeWinnenRondes;

    @FXML private Button AddDeelnemerInPoule;
    @FXML private Button RemoveDeelnemerInPoule;
    @FXML private Button AddPoule;
    @FXML private Button RemovePoule;
    @FXML private Button Opslaan;
    @FXML private Button Annuleren;
    @FXML private Button DeeltoernooiStarten;

    public PouleInplannenController(Toernooi toernooi, int deeltoernooinummer) {
        this.pouleInplannenModel = new PouleInplannenModel(toernooi, deeltoernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewDeelnemers();
        initializeTableViewPoules();
        initializeTableViewDeelnemersInPoule();
        initializeTableViewToernooiInfo();
        initializeButtons();
    }

    private void initializeTableViewDeelnemers(){
        DeelnemersBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnr"));
        DeelnemersVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
        DeelnemersAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
        DeelnemersLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));

        Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
    }

    private void initializeTableViewPoules(){
        PoulesPoulenummer.setCellValueFactory(new PropertyValueFactory<Poule, String>("naam"));
        if (pouleInplannenModel.getPoules() != null) {
            Poules.getItems().setAll(pouleInplannenModel.getPoules());
        }
    }

    private void initializeTableViewDeelnemersInPoule(){
        DeelnemersInPouleBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Bondsnr"));
        DeelnemersInPouleVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
        DeelnemersInPouleAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
        DeelnemersInPouleLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));
    }

    private void initializeTableViewToernooiInfo(){
        ToernooiNaamInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        ToernooiStartdatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("begindatum"));
        ToernooiEinddatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("einddatum"));

        ToernooiInfoTable.getItems().setAll(pouleInplannenModel.getToernooi());
    }

    private void initializeButtons(){
        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(pouleInplannenModel.getToernooi().getID());
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
        AddPoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pouleInplannenModel.addPoule();
                Poules.getItems().setAll(pouleInplannenModel.getPoules());
            }
        });

        RemovePoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Poule poule = Poules.getSelectionModel().getSelectedItem();
                if(poule != null){
                    pouleInplannenModel.removePoule(poule);
                }
                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
                DeelnemersInPoule.getItems().clear();
                Poules.getItems().setAll(pouleInplannenModel.getPoules());
            }
        });

        Poules.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
            }
        });

        AddDeelnemerInPoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pouleInplannenModel.addDeelnemerPoule(Deelnemers.getSelectionModel().getSelectedItem(), Poules.getSelectionModel().getSelectedItem());
                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
            }
        });

        RemoveDeelnemerInPoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pouleInplannenModel.removeDeelnemerPoule(DeelnemersInPoule.getSelectionModel().getSelectedItem(), Poules.getSelectionModel().getSelectedItem());
                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
            }
        });

        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pouleInplannenModel.saveDeelnemersInPoule();
            }
        });
        DeeltoernooiStarten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pouleInplannenModel.planEnSluitDeeltoernooiPlanning(Integer.parseInt(TeWinnenRondes.getText()));
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(pouleInplannenModel.getToernooi().getID());
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
    }
}
