package presentation.controllers;

import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.NieuwToernooiCommissieLeden;
import domain.Toernooitype;
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
import presentation.models.ToernooiModel;
import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @FXML ChoiceBox<String> SysteemType;

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
        CommisieLidToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwToernooiCommissieLeden selected = CommisieLeden.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    CommisieLidInToernooi commissieLid = new CommisieLidInToernooi();
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
                CommisieLidInToernooi selected = AddedCommisieLeden.getSelectionModel().getSelectedItem();
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
                toernooiModel.saveToernooi(toernooiID,ToernooiNaamValue.getText(), java.sql.Date.valueOf(BegindatumValue.getValue()), java.sql.Date.valueOf(EinddatumValue.getValue()), java.sql.Date.valueOf(InschrijfdatumValue.getValue()), Double.parseDouble(PrijsValue.getText()), BetalingsinformatieValue.getText(), SysteemType.getValue());
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
        PrijsValue.setText(String.valueOf(toernooiModel.getToernooi().getPrijs()));
        BetalingsinformatieValue.setText(toernooiModel.getToernooi().getBetalingsinformatie());
        PlaatsValue.setText(toernooiModel.getToernooi().getLocatie().getPlaats());
        StraatValue.setText(toernooiModel.getToernooi().getLocatie().getStraatnaam());
        HuisnummerValue.setText(toernooiModel.getToernooi().getLocatie().getHuisnummer());
    }

    private void initializeTableViewAddedCommisieLeden() {
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("naam"));
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

}
