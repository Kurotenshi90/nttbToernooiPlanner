package presentation.controllers;

import domain.Bracket;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.KnockoutInplannenModel;

import java.net.URL;
import java.util.Date;
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


    @FXML private TextField Speler1Bondsnr;
    @FXML private TextField Speler1Voornaam;
    @FXML private TextField Speler1Achternaam;
    @FXML private TextField Speler1Licentie;
    @FXML private TextField Speler2Bondsnr;
    @FXML private TextField Speler2Voornaam;
    @FXML private TextField Speler2Achternaam;
    @FXML private TextField Speler2Licentie;

    @FXML private TableView<Toernooi> ToernooiInfoTable;
    @FXML private TableColumn<Toernooi, String> ToernooiNaamInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiStartdatumInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiEinddatumInfo;


    @FXML private TableView<Bracket> Brackets;
    @FXML private TableColumn<Bracket, String> BracketsBracketnummer;

    @FXML private Button AddDeelnemer1InBracket;
    @FXML private Button RemoveDeelnemer1InBracket;
    @FXML private Button AddDeelnemer2InBracket;
    @FXML private Button RemoveDeelnemer2InBracket;
    @FXML private Button Opslaan;
    @FXML private Button Annuleren;

    public KnockoutInplannenController(Toernooi toernooi, int deeltoernooinummer) {
        this.knockoutInplannenModel = new KnockoutInplannenModel(toernooi, deeltoernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewDeelnemers();
        initializeTableViewBrackets();
        initializeTableViewToernooiInfo();
        initializeButtons();
        setTextfieldsUneditable();
    }

    private void initializeTableViewDeelnemers(){
        DeelnemersBondsnummer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnr"));
        DeelnemersVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Voornaam"));
        DeelnemersAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Achternaam"));
        DeelnemersLicentie.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("Licentie"));

        Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
    }

    private void initializeTableViewBrackets(){
        BracketsBracketnummer.setCellValueFactory(new PropertyValueFactory<Bracket, String>("bracketnr"));
        if (knockoutInplannenModel.getBrackets() != null) {
            Brackets.getItems().setAll(knockoutInplannenModel.getBrackets());
        }
    }

    private void initializeTableViewToernooiInfo(){
        ToernooiNaamInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        ToernooiStartdatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("begindatum"));
        ToernooiEinddatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("einddatum"));

        ToernooiInfoTable.getItems().setAll(knockoutInplannenModel.getToernooi());
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
                    if (bracket.getSpeler1().size() > 0) {
                        setTextFieldsSpeler1(bracket.getSpeler1().get(0));
                    } else {
                        setTextFieldsSpeler1Empty();
                    }
                    if (bracket.getSpeler2().size() > 0) {
                        setTextFieldsSpeler2(bracket.getSpeler2().get(0));
                    } else {
                        setTextFieldsSpeler2Emtpy();
                    }

                }

            }
        });

        AddDeelnemer1InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Brackets.getSelectionModel().getSelectedItem().getSpeler1().size() < 1) {
                    knockoutInplannenModel.addDeelnemerBracket(Deelnemers.getSelectionModel().getSelectedItem(), Brackets.getSelectionModel().getSelectedItem(), true);
                    Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                    setTextFieldsSpeler1(Brackets.getSelectionModel().getSelectedItem().getSpeler1().get(0));
                }
            }
        });

        AddDeelnemer2InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Brackets.getSelectionModel().getSelectedItem().getSpeler2().size() < 1) {
                    knockoutInplannenModel.addDeelnemerBracket(Deelnemers.getSelectionModel().getSelectedItem(), Brackets.getSelectionModel().getSelectedItem(), false);
                    Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                    setTextFieldsSpeler2(Brackets.getSelectionModel().getSelectedItem().getSpeler2().get(0));
                }
            }
        });

        RemoveDeelnemer1InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knockoutInplannenModel.removeDeelnemerBracket(Brackets.getSelectionModel().getSelectedItem().getSpeler1().get(0), Brackets.getSelectionModel().getSelectedItem(), true);
                Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                setTextFieldsSpeler1Empty();
            }
        });

        RemoveDeelnemer2InBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knockoutInplannenModel.removeDeelnemerBracket(Brackets.getSelectionModel().getSelectedItem().getSpeler2().get(0), Brackets.getSelectionModel().getSelectedItem(), false);
                Deelnemers.getItems().setAll(knockoutInplannenModel.getDeelnemers());
                setTextFieldsSpeler2Emtpy();
            }
        });

        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                knockoutInplannenModel.saveDeelnemersInPoule();
            }
        });
    }

    private void setTextFieldsSpeler2Emtpy() {
        Speler2Bondsnr.setText("");
        Speler2Voornaam.setText("");
        Speler2Achternaam.setText("");
        Speler2Licentie.setText("");
    }

    private void setTextFieldsSpeler1Empty() {
        Speler1Bondsnr.setText("");
        Speler1Voornaam.setText("");
        Speler1Achternaam.setText("");
        Speler1Licentie.setText("");
    }


    private void setTextFieldsSpeler1(Deelnemer deelnemer){
        Speler1Bondsnr.setText(deelnemer.getBondsnr() + "");
        Speler1Voornaam.setText(deelnemer.getVoornaam());
        Speler1Achternaam.setText(deelnemer.getAchternaam());
        Speler1Licentie.setText(deelnemer.getLicentie());

    }

    private void setTextFieldsSpeler2(Deelnemer deelnemer){
        Speler2Bondsnr.setText(deelnemer.getBondsnr() + "");
        Speler2Voornaam.setText(deelnemer.getVoornaam());
        Speler2Achternaam.setText(deelnemer.getAchternaam());
        Speler2Licentie.setText(deelnemer.getLicentie());

    }

    private void setTextfieldsUneditable() {
        Speler1Bondsnr.setEditable(false);
        Speler1Voornaam.setEditable(false);
        Speler1Achternaam.setEditable(false);
        Speler1Licentie.setEditable(false);
        Speler2Bondsnr.setEditable(false);
        Speler2Voornaam.setEditable(false);
        Speler2Achternaam.setEditable(false);
        Speler2Licentie.setEditable(false);
    }
}
