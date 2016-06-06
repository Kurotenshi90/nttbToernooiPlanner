package presentation.controllers;

import domain.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.BekijkToernooiModel;

import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 19-05-16.
 */
public class BekijkToernooiController implements Initializable{
    @FXML TextField ToernooiNaamValue;
    @FXML TextArea BetalingsinformatieValue;
    @FXML TextField InschrijfdatumValue;
    @FXML TextField BegindatumValue;
    @FXML TextField EinddatumValue;
    @FXML TextField PlaatsValue;
    @FXML TextField StraatValue;
    @FXML TextField HuisnummerValue;
    @FXML TextField ToernooisoortValue;

    @FXML TableView<CommissieLidInToernooi> Commissieleden;
    @FXML TableColumn<CommissieLidInToernooi, String> CommissieNaam;


    @FXML TableView<domain.Deeltoernooi> Deeltoernooi;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiSpelvorm;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiMaxSpelers;
    @FXML TableColumn<Deeltoernooi, Double> DeeltoernooiPrijs;
    @FXML TableColumn<Deeltoernooi, DateFormat> DeeltoernooiDatumTijd;


    @FXML TableView<Klasse> DeeltoernooiKlasse;
    @FXML TableColumn<Klasse, String> DeeltoernooiKlasseLeeftijd;
    @FXML TableColumn<Klasse, String> DeeltoernooiKlasseLicentie;


    @FXML Button Button_Terug;

    private int toernooiID;
    private BekijkToernooiModel toernooiModel;

    public BekijkToernooiController(int toernooiID){
        this.toernooiID = toernooiID;
        toernooiModel = new BekijkToernooiModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toernooiModel.getOneToernooi(toernooiID);
        setTextFields();
        initializeButtons();
        disableTextfields();
        initializeTableViewCommissieleden();
        initializeTableViewDeeltoernooi();
        initializeTableViewDeeltoernooiKlasse();
    }

    private void disableTextfields() {
        ToernooiNaamValue.setDisable(true);
        BetalingsinformatieValue.setDisable(true);
        InschrijfdatumValue.setDisable(true);
        BegindatumValue.setDisable(true);
        EinddatumValue.setDisable(true);
        PlaatsValue.setDisable(true);
        StraatValue.setDisable(true);
        HuisnummerValue.setDisable(true);
        ToernooisoortValue.setDisable(true);

        Commissieleden.setMouseTransparent(true);
        Commissieleden.setFocusTraversable(false);
        Deeltoernooi.setEditable(false);
        DeeltoernooiKlasse.setFocusTraversable(false);
        DeeltoernooiKlasse.setSelectionModel(null);

    }

    private void initializeButtons() {
        Button_Terug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/ToernooiBeheren.fxml"));
                ToernooiBeherenController controller = new ToernooiBeherenController();
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

        Deeltoernooi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                    DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                    DeeltoernooiKlasse.getItems().setAll(toernooiModel.getKlasses(deeltoernooi.getKlasses()));
                }
            }
        });
    }

    private void setTextFields() {
        ToernooiNaamValue.setText(toernooiModel.getToernooi().getNaam());
        BetalingsinformatieValue.setText(toernooiModel.getToernooi().getBetalingsinformatie());
        InschrijfdatumValue.setText(toernooiModel.getToernooi().getInschrijfdatum().toString());
        BegindatumValue.setText(toernooiModel.getToernooi().getBegindatum().toString());
        EinddatumValue.setText(toernooiModel.getToernooi().getEinddatum().toString());
        PlaatsValue.setText(toernooiModel.getToernooi().getLocatie().getPlaats());
        StraatValue.setText(toernooiModel.getToernooi().getLocatie().getStraatnaam());
        HuisnummerValue.setText(toernooiModel.getToernooi().getLocatie().getHuisnummer());
        ToernooisoortValue.setText(toernooiModel.getToernooi().getToernooisoort());
    }

    private void initializeTableViewCommissieleden() {
        CommissieNaam.setCellValueFactory(new PropertyValueFactory<CommissieLidInToernooi, String>("naam"));
        Commissieleden.getItems().setAll(toernooiModel.getToernooi().getCommisieLidInToernooi());
    }

    private  void initializeTableViewDeeltoernooi(){
        DeeltoernooiSpelvorm.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("spelvorm"));
        DeeltoernooiMaxSpelers.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("maxAantalSpelers"));
        DeeltoernooiPrijs.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, Double>("prijs"));
        DeeltoernooiDatumTijd.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, DateFormat>("beginTijd"));
        Deeltoernooi.getItems().setAll(toernooiModel.getToernooi().getDeeltoernoois());
    }

    private void initializeTableViewDeeltoernooiKlasse(){
        DeeltoernooiKlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        DeeltoernooiKlasseLicentie.setCellValueFactory(new PropertyValueFactory<domain.Klasse, String>("licentietype"));
    }

}
