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
import jfxtras.scene.control.LocalDateTimeTextField;
import presentation.models.ToernooiModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 12-05-16.
 */
public class UpdateToernooiController implements Initializable {
    @FXML Button CommisieLidToevoegen;
    @FXML Button CommisieLidVerwijderen;
    @FXML Button ToernooiAanmaken;
    @FXML Button Home;
    @FXML Button Annuleren;

    @FXML TableView<NieuwToernooiCommissieLeden> CommisieLeden;
    @FXML TableColumn<NieuwToernooiCommissieLeden, String> CommisieVoornaam;

    @FXML TableView<CommissieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommissieLidInToernooi, String> AddedCommisieVoornaam;
    @FXML TableColumn<CommissieLidInToernooi, String> AddedCommisieAchternaam;

    @FXML TableView<Locatie> LocatieTable;
    @FXML TableColumn<Locatie, String> Plaats;
    @FXML TableColumn<Locatie, String> Straat;
    @FXML TableColumn<Locatie, String> Huisnummer;

    @FXML TextField ToernooiNaamValue;
    @FXML TextField PrijsValue;
    @FXML TextArea BetalingsinformatieValue;
    @FXML TextField PlaatsValue;
    @FXML TextField StraatValue;
    @FXML TextField HuisnummerValue;

    @FXML DatePicker InschrijfdatumValue;
    @FXML DatePicker BegindatumValue;
    @FXML DatePicker EinddatumValue;

    @FXML ChoiceBox<String> SysteemType;

    @FXML TableView<domain.Deeltoernooi> Deeltoernooi;
    @FXML TableColumn<Deeltoernooi,String> DeeltoernooiSpelvorm;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiMaxSpelers;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiPrijs;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiTijd;
    @FXML TableView<Klasse> Klasse;
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

    @FXML
    LocalDateTimeTextField DatetimeDeeltoernooi;

    private int toernooiID;
    private ToernooiModel toernooiModel;

    public UpdateToernooiController(int toernooiID){
        this.toernooiID = toernooiID;
        toernooiModel = new ToernooiModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toernooiModel.getOneToernooi(toernooiID);
        setTextFields();
        initializeLocatieTable();
        initializeTableViewCommisieLeden();
        initializeTableViewAddedCommisieLeden();
        initializeButtons();
        initializeChoiceboxSysteemType();
        initializeTableViewKlasse();
        initializeChoiceBoxSpelvorm();
        initializeTableViewDeeltoernooi();
        initializeTableViewDeeltoernooiKlasse();

    }
    private void initializeTableViewDeeltoernooiKlasse(){
        DeeltoernooiKlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        DeeltoernooiKlasseLicentie.setCellValueFactory(new PropertyValueFactory<domain.Klasse, String>("licentietype"));
    }


    private  void initializeTableViewDeeltoernooi(){
        DeeltoernooiSpelvorm.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("spelvorm"));
        DeeltoernooiMaxSpelers.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("maxAantalSpelers"));
        DeeltoernooiPrijs.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("prijs"));
        DeeltoernooiTijd.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("beginTijd"));
        Deeltoernooi.getItems().setAll(toernooiModel.getDeeltoernoois());
    }

    private void initializeChoiceBoxSpelvorm(){
        ArrayList<String> spelvormen = new ArrayList<>();
        for(Spelvorm t : toernooiModel.getSpelvormen()){
            spelvormen.add(t.getSpelvorm());
        }
        Spelvorm.getItems().setAll(spelvormen);
    }

    private void initializeTableViewKlasse(){
        KlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        KlasseLicentie.setCellValueFactory(new PropertyValueFactory<Klasse, String>("licentietype"));
    }

    private void initializeChoiceboxSysteemType(){
        ArrayList<String> toernooitypes = new ArrayList<>();
        for(Toernooitype t : toernooiModel.getToernooitypes()){
            toernooitypes.add(t.getType());
        }
        SysteemType.getItems().setAll(toernooitypes);
        SysteemType.setValue(toernooiModel.getToernooi().getToernooisoort());
    }

    private void initializeButtons() {
        KlasseVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                deeltoernooi.getKlasses().remove(DeeltoernooiKlasse.getSelectionModel().getSelectedItem());
                DeeltoernooiKlasse.getItems().setAll(Deeltoernooi.getSelectionModel().getSelectedItem().getKlasses());
                Klasse.getItems().setAll(toernooiModel.getKlasses(deeltoernooi.getKlasses()));
            }
        });

        AddDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toernooiModel.addDeeltoernooi(new Deeltoernooi(Integer.parseInt(MaxAantalSpelers.getText()),0, DatetimeDeeltoernooi.getLocalDateTime(), Double.parseDouble(PrijsValue.getText()),Spelvorm.getValue().toString(), false));
                Deeltoernooi.getItems().setAll(toernooiModel.getDeeltoernoois());
            }
        });
        KlasseToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                deeltoernooi.addKlasse(Klasse.getSelectionModel().getSelectedItem());
                DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                Klasse.getItems().setAll(toernooiModel.getKlasses(deeltoernooi.getKlasses()));
            }
        });
        Deeltoernooi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                    DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                    Klasse.getItems().setAll(toernooiModel.getKlasses(deeltoernooi.getKlasses()));
                }
            }
        });
        DeleteDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Deeltoernooi> deeltoernooi = toernooiModel.getDeeltoernoois();
                deeltoernooi.remove(Deeltoernooi.getSelectionModel().getSelectedItem());
                Deeltoernooi.getItems().setAll(toernooiModel.getDeeltoernoois());
                DeeltoernooiKlasse.getItems().clear();
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
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/ToernooiBeheren.fxml"));
                ToernooiBeherenController controller = new ToernooiBeherenController();
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
        CommisieLidToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwToernooiCommissieLeden selected = CommisieLeden.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    CommissieLidInToernooi commissieLid = new CommissieLidInToernooi();
                    commissieLid.setNaam(selected.getNaam());
                    commissieLid.setLidnr(selected.getLidnr());
                    toernooiModel.addAddedCommisieLeden(commissieLid);
                    AddedCommisieLeden.getItems().setAll(toernooiModel.getAddedCommisieLeden());
                    CommisieLeden.getItems().setAll(toernooiModel.getNieuwToernooiCommissieLeden());
                }
            }
        });
        LocatieTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY){
                    Locatie locatie = LocatieTable.getSelectionModel().getSelectedItem();
                    PlaatsValue.setText(locatie.getPlaats());
                    StraatValue.setText(locatie.getStraatnaam());
                    HuisnummerValue.setText(locatie.getHuisnummer());
                    toernooiModel.setLocatie(locatie);
                }
            }
        });

        CommisieLidVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CommissieLidInToernooi selected = AddedCommisieLeden.getSelectionModel().getSelectedItem();
                if(selected !=null) {
                    toernooiModel.deleteCommissieLid(selected);
                    AddedCommisieLeden.getItems().setAll(toernooiModel.getAddedCommisieLeden());
                    CommisieLeden.getItems().setAll(toernooiModel.getNieuwToernooiCommissieLeden());
                }
            }
        });
        ToernooiAanmaken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(toernooiID);
                toernooiModel.saveToernooi(toernooiID,ToernooiNaamValue.getText(), java.sql.Date.valueOf(BegindatumValue.getValue()), java.sql.Date.valueOf(EinddatumValue.getValue()), java.sql.Date.valueOf(InschrijfdatumValue.getValue()), BetalingsinformatieValue.getText(), SysteemType.getValue());
                goToHome(ToernooiAanmaken);
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
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommissieLidInToernooi, String>("naam"));
        AddedCommisieLeden.getItems().setAll(toernooiModel.getAddedCommisieLeden());
    }

    private void initializeLocatieTable() {
        toernooiModel.setLocatie(toernooiModel.getToernooi().getLocatie());
        Plaats.setCellValueFactory(new PropertyValueFactory<Locatie, String>("plaats"));
        Straat.setCellValueFactory(new PropertyValueFactory<Locatie, String>("straatnaam"));
        Huisnummer.setCellValueFactory(new PropertyValueFactory<Locatie, String>("huisnummer"));
        LocatieTable.getItems().setAll(toernooiModel.getLocaties());
    }

    private void initializeTableViewCommisieLeden() {
        CommisieVoornaam.setCellValueFactory(new PropertyValueFactory<NieuwToernooiCommissieLeden, String>("naam"));
        CommisieLeden.getItems().setAll(toernooiModel.getNieuwToernooiCommissieLeden());
    }

    private void goToHome(Button button) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/ToernooiBeheren.fxml"));
        ToernooiBeherenController controller = new ToernooiBeherenController();
        loader.setController(controller);
        Stage stage = (Stage) button.getScene().getWindow();
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

}
