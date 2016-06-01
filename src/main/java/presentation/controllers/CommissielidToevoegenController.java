package presentation.controllers;

import domain.Commissielid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.models.CommissieModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dirk on 31-5-2016.
 */
public class CommissielidToevoegenController implements Initializable{
    @FXML TextField CommissielidNaam;
    @FXML TextField CommissielidStraatnaam;
    @FXML TextField CommissielidHuisnr;
    @FXML TextField CommissielidPostcode;
    @FXML TextField CommissielidEmail;

    @FXML Button Opslaan;
    @FXML Button Annuleren;

    private CommissieModel commissieModel;

    public CommissielidToevoegenController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commissieModel = new CommissieModel();
        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Commissielid commissielid = new Commissielid(0, CommissielidNaam.getText(), CommissielidStraatnaam.getText(), Integer.parseInt(CommissielidHuisnr.getText()), CommissielidPostcode.getText(), CommissielidEmail.getText());
                commissieModel.saveCommissielid(commissielid);
                closeStage(Opslaan);
            }
        });

        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(Annuleren);
            }
        });
    }

    private void closeStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
