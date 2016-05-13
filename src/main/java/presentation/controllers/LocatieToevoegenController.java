package presentation.controllers;

import domain.Locatie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.models.LocatieToevoegenModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 13-05-16.
 */
public class LocatieToevoegenController implements Initializable {
    @FXML TextField LocatieNaam;
    @FXML TextField LocatieWoonplaats;
    @FXML TextField LocatieStraat;
    @FXML TextField LocatieHuisnummer;
    @FXML TextField LocatiePostcode;
    @FXML TextField LocatieTelefoonnummer;

    @FXML Button LocatieOpslaan;
    @FXML Button LocatieAnnuleren;

    private LocatieToevoegenModel locatieToevoegenModel;
    private NieuwToernooiController nieuwToernooiController;

    public LocatieToevoegenController(NieuwToernooiController nieuwToernooiController){
        this.nieuwToernooiController = nieuwToernooiController;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locatieToevoegenModel = new LocatieToevoegenModel();
        LocatieOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Locatie locatie = new Locatie(0, LocatieWoonplaats.getText(), LocatieStraat.getText(),LocatieHuisnummer.getText(), LocatieNaam.getText(), LocatiePostcode.getText(), Integer.parseInt(LocatieTelefoonnummer.getText()));
                locatieToevoegenModel.saveLocatie(locatie);
                closeStage(LocatieOpslaan);
                nieuwToernooiController.loadLocaties(locatie);
            }
        });

        LocatieAnnuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(LocatieAnnuleren);
            }
        });

    }

    public void closeStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
