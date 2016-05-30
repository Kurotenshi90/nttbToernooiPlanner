package presentation.controllers;

import domain.Deelnemer;
import domain.Poule;
import domain.Toernooi;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.KnockoutInplannenModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class KnockoutInplannenController implements Initializable {
    private KnockoutInplannenModel knockoutInplannenModel;

    @FXML
    private TableView<Deelnemer> Deelnemers;
    @FXML private TableColumn<Deelnemer, String> DeelnemersBondsnummer;
    @FXML private TableColumn<Deelnemer, String> DeelnemersVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersAchternaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersLicentie;

    @FXML private TableView<Poule> Brackets;
    @FXML private TableColumn<Poule, String> BracketsBracketnummer;

    @FXML private TableView<Deelnemer> DeelnemersInBracket;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInBracketBondsnummer;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInBracketVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInBracketAchternaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInBracketLicentie;
    @FXML private TableColumn<Deelnemer, String> DeelnemersInBracketPoulenummer;

    @FXML private Button AddDeelnemerInBracket;
    @FXML private Button RemoveDeelnemerInBracket;
    @FXML private Button Opslaan;
    @FXML private Button Annuleren;
    @FXML private Button DeeltoernooiStarten;

    public KnockoutInplannenController(Toernooi toernooi, int deeltoernooinummer) {
        this.knockoutInplannenModel = new KnockoutInplannenModel(toernooi, deeltoernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewDeelnemers();
     //   initializeTableViewBrackets();
      //  initializeTableViewDeelnemersInPoule();
      //  initializeButtons();
    }

    private void initializeTableViewDeelnemers(){
        DeelnemersBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnr"));
        DeelnemersVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
        DeelnemersAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
        DeelnemersLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));

        Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
    }

//    private void initializeTableViewBrackets(){
//        BracketsBracketnummer.setCellValueFactory(new PropertyValueFactory<Poule, String>("bracketnr"));
//        if (knockoutInplannenModel.getBrackets() != null) {
//            Brackets.getItems().setAll(KnockoutInplannenModel.getBrackets());
//        }
//    }
//
//    private void initializeTableViewDeelnemersInPoule(){
//        DeelnemersInPouleBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Bondsnr"));
//        DeelnemersInPouleVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
//        DeelnemersInPouleAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
//        DeelnemersInPouleLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));
//    }
//
//    private void initializeButtons(){
//        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
//                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(pouleInplannenModel.getToernooi().getID());
//                loader.setController(controller);
//                Stage stage = (Stage) Annuleren.getScene().getWindow();
//                Parent root = null;
//                try {
//                    root = loader.load();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(root, 1920, 1080);
//                stage.setScene(scene);
//                stage.show();
//            }
//        });
//        AddPoule.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pouleInplannenModel.addPoule();
//                Poules.getItems().setAll(pouleInplannenModel.getPoules());
//            }
//        });
//
//        RemovePoule.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Poule poule = Poules.getSelectionModel().getSelectedItem();
//                if(poule != null){
//                    pouleInplannenModel.removePoule(poule);
//                }
//                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
//                DeelnemersInPoule.getItems().clear();
//                Poules.getItems().setAll(pouleInplannenModel.getPoules());
//            }
//        });
//
//        Poules.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
//            }
//        });
//
//        AddDeelnemerInPoule.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pouleInplannenModel.addDeelnemerPoule(Deelnemers.getSelectionModel().getSelectedItem(), Poules.getSelectionModel().getSelectedItem());
//                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
//                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
//            }
//        });
//
//        RemoveDeelnemerInPoule.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pouleInplannenModel.removeDeelnemerPoule(DeelnemersInPoule.getSelectionModel().getSelectedItem(), Poules.getSelectionModel().getSelectedItem());
//                Deelnemers.getItems().setAll(pouleInplannenModel.getDeelnemers());
//                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getDeelnemers());
//            }
//        });
//
//        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pouleInplannenModel.saveDeelnemersInPoule();
//                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
//                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(pouleInplannenModel.getToernooi().getID());
//                loader.setController(controller);
//                Stage stage = (Stage) Annuleren.getScene().getWindow();
//                Parent root = null;
//                try {
//                    root = loader.load();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(root, 1920, 1080);
//                stage.setScene(scene);
//                stage.show();
//            }
//        });
//        DeeltoernooiStarten.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pouleInplannenModel.planEnSluitDeeltoernooiPlanning();
//                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
//                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(pouleInplannenModel.getToernooi().getID());
//                loader.setController(controller);
//                Stage stage = (Stage) Annuleren.getScene().getWindow();
//                Parent root = null;
//                try {
//                    root = loader.load();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(root, 1920, 1080);
//                stage.setScene(scene);
//                stage.show();
//            }
//        });
//    }
}
