package presentation.controllers;

import domain.Bracket;
import domain.Deelnemer;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.KnockoutInplannenModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class KnockoutInplannenDubbelController implements Initializable {
    private KnockoutInplannenModel knockoutInplannenModel;

    @FXML
    private TableView<Deelnemer> Deelnemers;
    @FXML private TableColumn<Deelnemer, String> DeelnemersBondsnummer;
    @FXML private TableColumn<Deelnemer, String> DeelnemersVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersAchternaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemersLicentie;
    @FXML private TableColumn<Deelnemer, String> DeelnemersBondsnrPartner;


    @FXML private TextField Speler1Bondsnr;
    @FXML private TextField Speler1Voornaam;
    @FXML private TextField Speler1Achternaam;
    @FXML private TextField Speler1Licentie;
    @FXML private TextField Speler2Bondsnr;
    @FXML private TextField Speler2Voornaam;
    @FXML private TextField Speler2Achternaam;
    @FXML private TextField Speler2Licentie;

    @FXML private TextField Speler1BondsnrPartner;
    @FXML private TextField Speler1VoornaamPartner;
    @FXML private TextField Speler1AchternaamPartner;
    @FXML private TextField Speler1LicentiePartner;
    @FXML private TextField Speler2BondsnrPartner;
    @FXML private TextField Speler2VoornaamPartner;
    @FXML private TextField Speler2AchternaamPartner;
    @FXML private TextField Speler2LicentiePartner;


    @FXML private TableView<Bracket> Brackets;
    @FXML private TableColumn<Bracket, String> BracketsBracketnummer;

    @FXML private Button AddDeelnemer1InBracket;
    @FXML private Button RemoveDeelnemer1InBracket;
    @FXML private Button AddDeelnemer2InBracket;
    @FXML private Button RemoveDeelnemer2InBracket;
    @FXML private Button Opslaan;
    @FXML private Button Annuleren;
    @FXML private Button DeeltoernooiStarten;

    public KnockoutInplannenDubbelController(Toernooi toernooi, int deeltoernooinummer) {
        this.knockoutInplannenModel = new KnockoutInplannenModel(toernooi, deeltoernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewDeelnemers();
        initializeTableViewBrackets();
        initializeButtons();
        setTextfieldsUneditable();
    }

    private void initializeTableViewDeelnemers(){
        DeelnemersBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnr"));
        DeelnemersVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
        DeelnemersAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
        DeelnemersLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));
        DeelnemersBondsnrPartner.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnrPartner"));

        Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
    }

    private void initializeTableViewBrackets(){
        BracketsBracketnummer.setCellValueFactory(new PropertyValueFactory<Bracket, String>("bracketnr"));
        if (knockoutInplannenModel.getBrackets() != null) {
            Brackets.getItems().setAll(knockoutInplannenModel.getBrackets());
        }
    }



    private void initializeButtons(){
        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(knockoutInplannenModel.getToernooi().getID());
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

        Brackets.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            Bracket bracket = Brackets.getSelectionModel().getSelectedItem();
                if(bracket != null) {
                    if (bracket.getSpeler1().size() > 1) {
                        setTextFieldsSpeler1(bracket.getSpeler1().get(0), bracket.getSpeler1().get(1));
                    } else {
                        setTextFieldsSpeler1Empty();
                    }
                    if (bracket.getSpeler2().size() > 1) {
                        setTextFieldsSpeler2(bracket.getSpeler2().get(0), bracket.getSpeler2().get(1));
                    } else {
                        setTextFieldsSpeler2Emtpy();
                    }

                }

            }
        });

        AddDeelnemer1InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Brackets.getSelectionModel().getSelectedItem().getSpeler2().size() < 2) {
                    Bracket bracket = Brackets.getSelectionModel().getSelectedItem();
                    Deelnemer deelnemer = Deelnemers.getSelectionModel().getSelectedItem();
                    if (bracket != null && deelnemer != null) {
                        knockoutInplannenModel.addDeelnemerBracket(deelnemer, bracket, true);
                        Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                        setTextFieldsSpeler1(bracket.getSpeler1().get(0), bracket.getSpeler1().get(1));
                    }
                }
            }
        });

        AddDeelnemer2InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Brackets.getSelectionModel().getSelectedItem().getSpeler2().size() < 2) {
                    Bracket bracket = Brackets.getSelectionModel().getSelectedItem();
                    Deelnemer deelnemer = Deelnemers.getSelectionModel().getSelectedItem();
                    if (bracket != null && deelnemer != null) {
                        knockoutInplannenModel.addDeelnemerBracket(deelnemer, bracket, false);
                        Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                        setTextFieldsSpeler2(bracket.getSpeler2().get(0), bracket.getSpeler2().get(1));
                    }
                }
            }
        });

        RemoveDeelnemer1InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bracket bracket = Brackets.getSelectionModel().getSelectedItem();
                knockoutInplannenModel.removeDeelnemerBracket(bracket.getSpeler1().get(0), bracket, true);
                Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                setTextFieldsSpeler1Empty();
            }
        });

        RemoveDeelnemer2InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bracket bracket = Brackets.getSelectionModel().getSelectedItem();
                knockoutInplannenModel.removeDeelnemerBracket(bracket.getSpeler2().get(0), bracket, false);
                Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                setTextFieldsSpeler2Emtpy();
            }
        });

        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knockoutInplannenModel.saveDeelnemersInPoule();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(knockoutInplannenModel.getToernooi().getID());
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
    }

    private void setTextFieldsSpeler2Emtpy() {
        Speler2Bondsnr.setText("");
        Speler2Voornaam.setText("");
        Speler2Achternaam.setText("");
        Speler2Licentie.setText("");
        Speler2BondsnrPartner.setText("");
        Speler2VoornaamPartner.setText("");
        Speler2AchternaamPartner.setText("");
        Speler2LicentiePartner.setText("");
    }

    private void setTextFieldsSpeler1Empty() {
        Speler1Bondsnr.setText("");
        Speler1Voornaam.setText("");
        Speler1Achternaam.setText("");
        Speler1Licentie.setText("");
        Speler1BondsnrPartner.setText("");
        Speler1VoornaamPartner.setText("");
        Speler1AchternaamPartner.setText("");
        Speler1LicentiePartner.setText("");
    }


    private void setTextFieldsSpeler1(Deelnemer deelnemer, Deelnemer deelnemer1){
        Speler1Bondsnr.setText(deelnemer.getBondsnr() + "");
        Speler1Voornaam.setText(deelnemer.getVoornaam());
        Speler1Achternaam.setText(deelnemer.getAchternaam());
        Speler1Licentie.setText(deelnemer.getLicentie());
        Speler1BondsnrPartner.setText(deelnemer1.getBondsnr() + "");
        Speler1VoornaamPartner.setText(deelnemer1.getVoornaam());
        Speler1AchternaamPartner.setText(deelnemer1.getAchternaam());
        Speler1LicentiePartner.setText(deelnemer1.getLicentie());

    }

    private void setTextFieldsSpeler2(Deelnemer deelnemer, Deelnemer deelnemer1){
        Speler2Bondsnr.setText(deelnemer.getBondsnr() + "");
        Speler2Voornaam.setText(deelnemer.getVoornaam());
        Speler2Achternaam.setText(deelnemer.getAchternaam());
        Speler2Licentie.setText(deelnemer.getLicentie());
        Speler2BondsnrPartner.setText(deelnemer1.getBondsnr() + "");
        Speler2VoornaamPartner.setText(deelnemer1.getVoornaam());
        Speler2AchternaamPartner.setText(deelnemer1.getAchternaam());
        Speler2LicentiePartner.setText(deelnemer1.getLicentie());
    }

    private void setTextfieldsUneditable() {
        Speler1Bondsnr.setEditable(false);
        Speler1Voornaam.setEditable(false);
        Speler1Achternaam.setEditable(false);
        Speler1Licentie.setEditable(false);
        Speler1BondsnrPartner.setEditable(false);
        Speler1VoornaamPartner.setEditable(false);
        Speler1AchternaamPartner.setEditable(false);
        Speler1LicentiePartner.setEditable(false);

        Speler2Bondsnr.setEditable(false);
        Speler2Voornaam.setEditable(false);
        Speler2Achternaam.setEditable(false);
        Speler2Licentie.setEditable(false);
        Speler2BondsnrPartner.setEditable(false);
        Speler2VoornaamPartner.setEditable(false);
        Speler2AchternaamPartner.setEditable(false);
        Speler2LicentiePartner.setEditable(false);
    }
}
