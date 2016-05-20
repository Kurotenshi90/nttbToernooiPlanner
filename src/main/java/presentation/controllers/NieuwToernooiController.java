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
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.models.ToernooiModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class NieuwToernooiController implements Initializable {
    @FXML Button CommisieLidToevoegen;
    @FXML Button CommisieLidVerwijderen;
    @FXML Button ToernooiAanmaken;
    @FXML Button Home;
    @FXML Button Annuleren;
    @FXML Button NieuweLocatieToevoegen;

    @FXML TableView<NieuwToernooiCommissieLeden> CommisieLeden;
    @FXML TableColumn<NieuwToernooiCommissieLeden, String> CommisieVoornaam;

    @FXML TableView<CommisieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieVoornaam;

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

    @FXML TableView<Deeltoernooi> Deeltoernooi;
    @FXML TableColumn<Deeltoernooi,String> DeeltoernooiSpelvorm;
    @FXML TableColumn<Deeltoernooi, String> DeeltoernooiMaxSpelers;
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

    private ToernooiModel nieuwToernooiModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nieuwToernooiModel = new ToernooiModel();
        initializeButtons();
        initializeTableViewCommisieLeden();
        initializeTableViewAddedCommisieLeden();
        initializeLocatieTable();
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

    private void initializeLocatieTable() {
        Plaats.setCellValueFactory(new PropertyValueFactory<Locatie, String>("plaats"));
        Straat.setCellValueFactory(new PropertyValueFactory<Locatie, String>("straatnaam"));
        Huisnummer.setCellValueFactory(new PropertyValueFactory<Locatie, String>("huisnummer"));
        LocatieTable.getItems().setAll(nieuwToernooiModel.getLocaties());
    }

    private void initializeChoiceBoxSpelvorm(){
        ArrayList<String> spelvormen = new ArrayList<>();
        for(Spelvorm t : nieuwToernooiModel.getSpelvormen()){
            spelvormen.add(t.getSpelvorm());
        }
        Spelvorm.getItems().setAll(spelvormen);
    }

    private void initializeTableViewKlasse(){
        KlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        KlasseLicentie.setCellValueFactory(new PropertyValueFactory<Klasse, String>("licentietype"));
    }

    private void initializeTableViewAddedCommisieLeden() {
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("naam"));
        AddedCommisieLeden.getItems().setAll(nieuwToernooiModel.getAddedCommisieLeden());
    }
    private  void initializeTableViewDeeltoernooi(){
        DeeltoernooiSpelvorm.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("spelvorm"));
        DeeltoernooiMaxSpelers.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("maxAantalSpelers"));
        Deeltoernooi.getItems().setAll(nieuwToernooiModel.getDeeltoernoois());
    }

    private void initializeChoiceboxSysteemType(){
        ArrayList<String> toernooitypes = new ArrayList<>();
        for(Toernooitype t : nieuwToernooiModel.getToernooitypes()){
            toernooitypes.add(t.getType());
        }
        SysteemType.getItems().setAll(toernooitypes);
    }

    private void initializeButtons() {
        DeleteDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Deeltoernooi> deeltoernooi = nieuwToernooiModel.getDeeltoernoois();
                deeltoernooi.remove(Deeltoernooi.getSelectionModel().getSelectedItem());
                Deeltoernooi.getItems().setAll(nieuwToernooiModel.getDeeltoernoois());
                DeeltoernooiKlasse.getItems().clear();
            }
        });

        KlasseVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                deeltoernooi.getKlasses().remove(DeeltoernooiKlasse.getSelectionModel().getSelectedItem());
                DeeltoernooiKlasse.getItems().setAll(Deeltoernooi.getSelectionModel().getSelectedItem().getKlasses());
                Klasse.getItems().setAll(nieuwToernooiModel.getKlasses(deeltoernooi.getKlasses()));
            }
        });

        AddDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nieuwToernooiModel.addDeeltoernooi(new Deeltoernooi(false,0,Integer.parseInt(MaxAantalSpelers.getText()),Spelvorm.getValue().toString(),));
                Deeltoernooi.getItems().setAll(nieuwToernooiModel.getDeeltoernoois());
            }
        });

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
        ToernooiAanmaken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nieuwToernooiModel.saveToernooi(0, ToernooiNaamValue.getText(), java.sql.Date.valueOf(BegindatumValue.getValue()), java.sql.Date.valueOf(EinddatumValue.getValue()), java.sql.Date.valueOf(InschrijfdatumValue.getValue()), BetalingsinformatieValue.getText(), SysteemType.getValue());
                goToHome(ToernooiAanmaken);
            }
        });

        LocatieTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Locatie locatie = LocatieTable.getSelectionModel().getSelectedItem();
                    PlaatsValue.setText(locatie.getPlaats());
                    StraatValue.setText(locatie.getStraatnaam());
                    HuisnummerValue.setText(locatie.getHuisnummer());
                    nieuwToernooiModel.setLocatie(locatie);
                }
            }
        });

        Deeltoernooi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                    DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                    Klasse.getItems().setAll(nieuwToernooiModel.getKlasses(deeltoernooi.getKlasses()));
                }
            }
        });

        KlasseToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                deeltoernooi.addKlasse(Klasse.getSelectionModel().getSelectedItem());
                DeeltoernooiKlasse.getItems().setAll(deeltoernooi.getKlasses());
                Klasse.getItems().setAll(nieuwToernooiModel.getKlasses(deeltoernooi.getKlasses()));
            }
        });

        CommisieLidVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CommisieLidInToernooi selected = AddedCommisieLeden.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    nieuwToernooiModel.deleteCommissieLid(selected);
                    AddedCommisieLeden.getItems().setAll(nieuwToernooiModel.getAddedCommisieLeden());
                    CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
                }
            }
        });

        NieuweLocatieToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/LocatieToevoegen.fxml"));
                LocatieToevoegenController controller = new LocatieToevoegenController(getController());
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                Scene scene = new Scene(root, 480, 320);
                stage.setScene(scene);
                stage.initOwner(NieuweLocatieToevoegen.getScene().getWindow());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            }
        });


    }

    private void initializeTableViewCommisieLeden() {
        CommisieVoornaam.setCellValueFactory(new PropertyValueFactory<NieuwToernooiCommissieLeden, String>("naam"));
        CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
    }

    private void goToHome(Button button) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/Home.fxml"));
        HomeController controller = new HomeController();
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

    private NieuwToernooiController getController() {
        return this;
    }

    public void loadLocaties(Locatie locatie) {
        nieuwToernooiModel.loadLocaties();
        LocatieTable.getItems().clear();
        LocatieTable.getItems().setAll(nieuwToernooiModel.getLocaties());
        PlaatsValue.setText(locatie.getPlaats());
        StraatValue.setText(locatie.getStraatnaam());
        HuisnummerValue.setText(locatie.getHuisnummer());
    }
}
