package presentation.controllers;

import domain.CommisieLid;
import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.NieuwToernooiCommissieLeden;
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
import main.Main;
import presentation.models.NieuwToernooiModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class NiewToernooiController implements Initializable {
    @FXML Button CommisieLidToevoegen;
    @FXML Button CommisieLidVerwijderen;
    @FXML Button ToernooiAanmaken;

    @FXML TableView<NieuwToernooiCommissieLeden> CommisieLeden;
    @FXML TableColumn<NieuwToernooiCommissieLeden, String> CommisieVoornaam;

    @FXML TableView<CommisieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieVoornaam;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieAchternaam;

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



    private NieuwToernooiModel nieuwToernooiModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nieuwToernooiModel = new NieuwToernooiModel();
        initializeButtons();
        initializeTableViewCommisieLeden();
        initializeTableViewAddedCommisieLeden();
        initializeLocatieTable();
    }

    private void initializeLocatieTable() {
        Plaats.setCellValueFactory(new PropertyValueFactory<Locatie, String>("plaats"));
        Straat.setCellValueFactory(new PropertyValueFactory<Locatie, String>("straatnaam"));
        Huisnummer.setCellValueFactory(new PropertyValueFactory<Locatie, String>("huisnummer"));
        LocatieTable.getItems().setAll(nieuwToernooiModel.getLocaties());
    }

    private void initializeTableViewAddedCommisieLeden() {
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("naam"));
        AddedCommisieLeden.getItems().setAll(nieuwToernooiModel.getAddedCommisieLeden());
    }

    private void initializeButtons() {
        CommisieLidToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwToernooiCommissieLeden selected = CommisieLeden.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    CommisieLidInToernooi commissieLid = new CommisieLidInToernooi();
                    commissieLid.setNaam(selected.getNaam());
                    commissieLid.setLidnr(selected.getLidnr());
                    nieuwToernooiModel.addAddedCommisieLeden(commissieLid);
                    AddedCommisieLeden.getItems().setAll(nieuwToernooiModel.getAddedCommisieLeden());
                    CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
                }
            }
        });

        ToernooiAanmaken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               nieuwToernooiModel.saveToernooi(ToernooiNaamValue.getText(), java.sql.Date.valueOf(BegindatumValue.getValue()), java.sql.Date.valueOf(EinddatumValue.getValue()), java.sql.Date.valueOf(InschrijfdatumValue.getValue()), Double.parseDouble(PrijsValue.getText()), BetalingsinformatieValue.getText(), "Knockout");
                goToHome(ToernooiAanmaken);
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
                    nieuwToernooiModel.setLocatie(locatie);
                }
            }
        });

        CommisieLidVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CommisieLidInToernooi selected = AddedCommisieLeden.getSelectionModel().getSelectedItem();
                if(selected !=null) {
                    nieuwToernooiModel.deleteCommissieLid(selected);
                    AddedCommisieLeden.getItems().setAll(nieuwToernooiModel.getAddedCommisieLeden());
                    CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
                }
            }
        });


    }

    private void initializeTableViewCommisieLeden() {
        CommisieVoornaam.setCellValueFactory(new PropertyValueFactory<NieuwToernooiCommissieLeden, String>("naam"));
        CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
    }

    private void goToHome(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Home.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}
