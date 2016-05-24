package presentation.controllers;

import domain.Locatie;
import domain.Vereniging;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.models.LocatieToevoegenModel;
import presentation.models.SpelersInschrijvenModel;
import presentation.models.VerenigingToevoegenModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 13-05-16.
 */
public class VerenigingToevoegenController implements Initializable {
    @FXML TextField VerenigingNaam;
    @FXML TextField VerenigingWoonplaats;
    @FXML TextField VerenigingStraat;
    @FXML TextField VerenigingHuisnummer;
    @FXML TextField VerenigingPostcode;
    @FXML TextField VerenigingTelefoonnummer;
    @FXML TextField VerenigingEmailAdres;

    @FXML Button VerenigingOpslaan;
    @FXML Button VerenigingAnnuleren;

    private VerenigingToevoegenModel verenigingToevoegenModel;
    private SpelersInschrijvenController spelersInschrijvenController;

    public VerenigingToevoegenController(SpelersInschrijvenController spelersInschrijvenController){
        this.spelersInschrijvenController = spelersInschrijvenController;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verenigingToevoegenModel = new VerenigingToevoegenModel();
        VerenigingOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Vereniging vereniging = new Vereniging(VerenigingNaam.getText(), VerenigingStraat.getText(), Integer.parseInt(VerenigingHuisnummer.getText()),VerenigingWoonplaats.getText(), VerenigingEmailAdres.getText(), VerenigingPostcode.getText(), Integer.parseInt(VerenigingTelefoonnummer.getText()));
                verenigingToevoegenModel.saveVereniging(vereniging);
                closeStage(VerenigingOpslaan);
                spelersInschrijvenController.loadVerenigingen();
            }
        });

        VerenigingAnnuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(VerenigingAnnuleren);
            }
        });

    }

    public void closeStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
