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
import presentation.models.ToernooiModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 19-05-16.
 */
public class BekijkToernooiController implements Initializable{
    @FXML
    Button CommisieLidToevoegen;
    @FXML Button CommisieLidVerwijderen;
    @FXML Button ToernooiAanmaken;
    @FXML Button Home;
    @FXML Button Annuleren;

    @FXML
    TableView<NieuwToernooiCommissieLeden> CommisieLeden;
    @FXML
    TableColumn<NieuwToernooiCommissieLeden, String> CommisieVoornaam;

    @FXML TableView<CommisieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieVoornaam;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieAchternaam;

    @FXML TableView<Locatie> LocatieTable;
    @FXML TableColumn<Locatie, String> Plaats;
    @FXML TableColumn<Locatie, String> Straat;
    @FXML TableColumn<Locatie, String> Huisnummer;

    @FXML
    TextField ToernooiNaamValue;
    @FXML TextField PrijsValue;
    @FXML
    TextArea BetalingsinformatieValue;
    @FXML TextField PlaatsValue;
    @FXML TextField StraatValue;
    @FXML TextField HuisnummerValue;

    @FXML DatePicker InschrijfdatumValue;
    @FXML DatePicker BegindatumValue;
    @FXML DatePicker EinddatumValue;

    @FXML ChoiceBox<String> SysteemType;

    @FXML TableView<domain.Deeltoernooi> Deeltoernooi;
    @FXML TableColumn<domain.Deeltoernooi,String> DeeltoernooiSpelvorm;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiMaxSpelers;
    @FXML TableView<domain.Klasse> Klasse;
    @FXML TableColumn<Klasse, String> KlasseLeeftijd;
    @FXML TableColumn<Klasse, String> KlasseLicentie;
    @FXML TableView<Klasse> DeeltoernooiKlasse;
    @FXML TableColumn<Klasse, String> DeeltoernooiKlasseLeeftijd;
    @FXML TableColumn<Klasse, String> DeeltoernooiKlasseLicentie;
    @FXML ChoiceBox Spelvorm;
    @FXML TextField MaxAantalSpelers;

    @FXML Button AddDeeltoernooi;
    @FXML Button KlasseToevoegen;
    @FXML Button KlasseVerwijderen;
    @FXML Button DeleteDeeltoernooi;
    @FXML Button NieuweLocatieToevoegen;

    @FXML Label MaxAantalSpelersLabel;
    @FXML Label SpelvormLabel;
    @FXML Label LocatieLabel;

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
        initializeTableViewAddedCommisieLeden();
        initializeChoiceboxSysteemType();
        initializeTableViewDeeltoernooi();
        initializeTableViewDeeltoernooiKlasse();
        initializeButtons();
        disableTextfields();
        setInvisable();
    }

    private void disableTextfields() {
        InschrijfdatumValue.setDisable(true);
        BegindatumValue.setDisable(true);
        EinddatumValue.setDisable(true);
        ToernooiNaamValue.editableProperty().setValue(false);
        PrijsValue.editableProperty().setValue(false);
        BetalingsinformatieValue.editableProperty().setValue(false);
        PlaatsValue.editableProperty().setValue(false);
        StraatValue.editableProperty().setValue(false);
        HuisnummerValue.editableProperty().setValue(false);
    }

    private void setInvisable(){
        AddedCommisieLeden.setDisable(true);
        AddDeeltoernooi.setVisible(false);
        KlasseToevoegen.setVisible(false);
        KlasseVerwijderen.setVisible(false);
        DeleteDeeltoernooi.setVisible(false);
        CommisieLidVerwijderen.setVisible(false);
        SysteemType.setDisable(true);
        Spelvorm.setVisible(false);
        MaxAantalSpelers.setVisible(false);
        ToernooiAanmaken.setVisible(false);
        CommisieLidToevoegen.setVisible(false);
        LocatieTable.setVisible(false);
        CommisieLeden.setVisible(false);
        NieuweLocatieToevoegen.setVisible(false);
        Klasse.setVisible(false);
        MaxAantalSpelersLabel.setVisible(false);
        SpelvormLabel.setVisible(false);
        LocatieLabel.setVisible(false);
    }

    private void initializeButtons() {
        Deeltoernooi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                    DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                }
            }
        });
        Home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Menu.fxml"));
                MenuController controller = new MenuController();
                loader.setController(controller);
                Stage stage = (Stage) Home.getScene().getWindow();
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
        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Home.fxml"));
                HomeController controller = new HomeController();
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

    private void setTextFields() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toernooiModel.getToernooi().getInschrijfdatum());
        InschrijfdatumValue.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH)));
        cal.setTime(toernooiModel.getToernooi().getBegindatum());
        BegindatumValue.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH)));
        cal.setTime(toernooiModel.getToernooi().getEinddatum());
        EinddatumValue.setValue(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH)));

        ToernooiNaamValue.setText(toernooiModel.getToernooi().getNaam());
        BetalingsinformatieValue.setText(toernooiModel.getToernooi().getBetalingsinformatie());
        PlaatsValue.setText(toernooiModel.getToernooi().getLocatie().getPlaats());
        StraatValue.setText(toernooiModel.getToernooi().getLocatie().getStraatnaam());
        HuisnummerValue.setText(toernooiModel.getToernooi().getLocatie().getHuisnummer());
    }

    private void initializeTableViewAddedCommisieLeden() {
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("naam"));
        AddedCommisieLeden.getItems().setAll(toernooiModel.getToernooi().getCommisieLidInToernooi());
    }


    private void initializeChoiceboxSysteemType(){
        ArrayList<String> toernooitypes = new ArrayList<>();
        for(Toernooitype t : toernooiModel.getToernooitypes()){
            toernooitypes.add(t.getType());
        }
        SysteemType.getItems().setAll(toernooitypes);
        SysteemType.setValue(toernooiModel.getToernooi().getToernooisoort());
    }

    private  void initializeTableViewDeeltoernooi(){
        DeeltoernooiSpelvorm.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("spelvorm"));
        DeeltoernooiMaxSpelers.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("maxAantalSpelers"));
        Deeltoernooi.getItems().setAll(toernooiModel.getToernooi().getDeeltoernoois());
    }

    private void initializeTableViewDeeltoernooiKlasse(){
        DeeltoernooiKlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        DeeltoernooiKlasseLicentie.setCellValueFactory(new PropertyValueFactory<domain.Klasse, String>("licentietype"));
    }

}
