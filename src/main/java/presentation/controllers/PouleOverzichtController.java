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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.PouleInplannenModel;
import presentation.models.PouleOverzichtModel;

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

    @FXML private TableView<PouleOverzicht> Poules;
    @FXML private TableColumn<PouleOverzicht, String> PouleNummer;

    @FXML private TableView<SpelerInPoule> DeelnemersInPoule;
    @FXML private TableColumn<SpelerInPoule, String> DeelnemerVoornaam;
    @FXML private TableColumn<SpelerInPoule, String> DeelnemerAchternaam;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerBondsnr;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerGewonnenWedstrijd;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerGewonnenRondes;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerPuntenVoor;
    @FXML private TableColumn<SpelerInPoule, Integer> DeelnemerPuntenTegen;

    @FXML private Button Button_Terug;

    private PouleOverzichtModel pouleOverzichtModel;
    private Toernooi toernooi;

    public PouleOverzichtController(Toernooi toernooi, int deeltoernooinummer) {
        this.pouleOverzichtModel = new PouleOverzichtModel(toernooi, deeltoernooinummer);
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
        Poules.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DeelnemersInPoule.getItems().setAll(Poules.getSelectionModel().getSelectedItem().getSpelerInPoules());
            }
        });
    }

    private void initializeTableViewToernooiInfo(){
        ToernooiNaamInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        ToernooiStartdatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("begindatum"));
        ToernooiEinddatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("einddatum"));

        ToernooiInfoTable.getItems().setAll(pouleOverzichtModel.getToernooi());
    }

    private void initializeTableViewPoules(){
        PouleNummer.setCellValueFactory(new PropertyValueFactory<PouleOverzicht, String>("naam"));
        if (pouleOverzichtModel.getPouleOverzichts() != null) {
            Poules.getItems().setAll(pouleOverzichtModel.getSpelersInPoule());
        }
    }

    private void initializeSpelersInPoule(){
        DeelnemerVoornaam.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, String>("voornaam"));
        DeelnemerAchternaam.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, String>("achternaam"));
        DeelnemerBondsnr.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("bondsnr"));
        DeelnemerGewonnenWedstrijd.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("gewonnenWedstrijden"));
        DeelnemerGewonnenRondes.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("gewonnenRondes"));
        DeelnemerPuntenVoor.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("puntenVoor"));
        DeelnemerPuntenTegen.setCellValueFactory(new PropertyValueFactory<SpelerInPoule, Integer>("puntenTegen"));
    }
}
