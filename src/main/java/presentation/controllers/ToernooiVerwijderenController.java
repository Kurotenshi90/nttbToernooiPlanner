package presentation.controllers;

import domain.Toernooi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.models.ToernooiModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dirk on 1-6-2016.
 */
public class ToernooiVerwijderenController implements Initializable {
    @FXML Button JaButton;
    @FXML Button NeeButton;

    private int toernooiId;
    private ToernooiModel toernooiModel;
    private ToernooiBeherenController toernooibeherenController;


    public ToernooiVerwijderenController(int toernooiId, ToernooiBeherenController toernooibeherenController) {
        this.toernooiId = toernooiId;
        this.toernooibeherenController = toernooibeherenController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
    }

    private void initializeButtons(){
        JaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toernooiModel = new ToernooiModel();
                toernooiModel.deleteToernooi(toernooiId);

                closeStage(JaButton);

                toernooibeherenController.refreshToernooiBeheren();

            }
        });

        NeeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(NeeButton);
            }
        });
    }

    private void closeStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
