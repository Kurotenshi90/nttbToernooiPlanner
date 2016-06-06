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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import presentation.models.PouleInplannenModel;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by dirk on 3-6-2016.
 */
public class PouleOverzichtController implements Initializable {
    @FXML private TableView<Toernooi> ToernooiInfoTable;
    @FXML private TableColumn<Toernooi, String> ToernooiNaamInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiStartdatumInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiEinddatumInfo;

    @FXML private TableView<Poule> Poules;
    @FXML private TableColumn<Poule, String> PouleNummer;

    @FXML private TableView<SpelerInPoule> DeelnemersInPoule;
    @FXML private TableColumn<Deelnemer, String> DeelnemerVoornaam;
    @FXML private TableColumn<Deelnemer, String> DeelnemerAchternaam;
    @FXML private TableColumn<Deelnemer, Integer> DeelnemerBondsnr;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerGewonnenWedstrijd;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerGewonnenRondes;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerPuntenVoor;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerPuntenTegen;

    @FXML private Button Button_Terug;

    private PouleInplannenModel pouleInplannenModel;
    private Toernooi toernooi;

    public PouleOverzichtController(Toernooi toernooi, int deeltoernooinummer) {
        this.pouleInplannenModel = new PouleInplannenModel(toernooi, deeltoernooinummer);
        this.toernooi = toernooi;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
        initializeTableViewToernooiInfo();
        initializeTableViewPoules();
        initializeSpelersInPoule();

        ToernooiInfoTable.setMouseTransparent(true);
        ToernooiInfoTable.setFocusTraversable(false);
        DeelnemersInPoule.setSelectionModel(null);
        DeelnemersInPoule.setFocusTraversable(false);
    }

    private void initializeButtons() {
        Button_Terug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(toernooi.getID());
                loader.setController(controller);
                Stage stage = (Stage) Button_Terug.getScene().getWindow();
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

    private void initializeTableViewToernooiInfo(){
        ToernooiNaamInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        ToernooiStartdatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("begindatum"));
        ToernooiEinddatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("einddatum"));

        ToernooiInfoTable.getItems().setAll(pouleInplannenModel.getToernooi());
    }

    private void initializeTableViewPoules(){
        PouleNummer.setCellValueFactory(new PropertyValueFactory<Poule, String>("naam"));
        if (pouleInplannenModel.getPoules() != null) {
            Poules.getItems().setAll(pouleInplannenModel.getPoules());
        }
    }

    private void initializeSpelersInPoule(){
        DeelnemerVoornaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("voornaam"));
        DeelnemerAchternaam.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("achternaam"));
        DeelnemerBondsnr.setCellValueFactory(new PropertyValueFactory<Deelnemer, Integer>("bondsnr"));
        DeelnemerGewonnenWedstrijd.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("gewonnenWedstrijden"));
        DeelnemerGewonnenRondes.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("gewonnenRondes"));
        DeelnemerPuntenVoor.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("puntenVoor"));
        DeelnemerPuntenTegen.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("puntenTegen"));
    }
}
