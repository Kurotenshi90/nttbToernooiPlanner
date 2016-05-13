package presentation.controllers;

import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.NieuwToernooiCommissieLeden;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.models.ToernooiModel;
import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 12-05-16.
 */
public class UpdateToernooiController implements Initializable {
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

    private int toernooiID;
    private ToernooiModel toernooiModel;

    public UpdateToernooiController(int toernooiID){
        this.toernooiID = toernooiID;
        toernooiModel = new ToernooiModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFields();
        System.out.println(toernooiModel.getToernooi().getNaam());

    }

    private void setTextFields() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toernooiModel.getToernooi().getBegindatum());
        toernooiModel.getOneToernooi(toernooiID);
        ToernooiNaamValue.setText(toernooiModel.getToernooi().getNaam());
        PrijsValue.setText(String.valueOf(toernooiModel.getToernooi().getPrijs()));
        BetalingsinformatieValue.setText(toernooiModel.getToernooi().getBetalingsinformatie());
        PlaatsValue.setText(toernooiModel.getToernooi().getLocatie().getPlaats());
        StraatValue.setText(toernooiModel.getToernooi().getLocatie().getStraatnaam());
        HuisnummerValue.setText(toernooiModel.getToernooi().getLocatie().getHuisnummer());
       // InschrijfdatumValue.setValue(LocalDateTime.ofInstant();
        BegindatumValue = new DatePicker(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
       // EinddatumValue.setValue();

    }


}
