package presentation.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 13-06-16.
 */
public class SetDialogController implements Initializable {
    @FXML
    Button okButton;

    @FXML
    Label messageLabel;

    String text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(text != null) {
            messageLabel.setText(text);
        }
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

    }

    public void setlabel(String text){
        this.text = text;
    }
}
