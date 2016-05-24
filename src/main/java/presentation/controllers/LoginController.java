package presentation.controllers;

import datasource.DAO.GetRoleDatabase;
import domain.Locatie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.models.LocatieToevoegenModel;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 13-05-16.
 */
public class LoginController implements Initializable {
    @FXML TextField Username;
    @FXML PasswordField Password;

    @FXML Button LoginAnnuleren;
    @FXML Button Login;

    private MenuController MenuController;
    private GetRoleDatabase getRoleDatabase;

    public LoginController(MenuController menuController){
        this.MenuController = menuController;
        this.getRoleDatabase = new GetRoleDatabase();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String file = getClass().getClassLoader().getResource("databaseproperties/DatabaseProperties").getFile();

                    FileInputStream in = new FileInputStream(file);
                    Properties props = new Properties();
                    props.load(in);
                    in.close();

                    FileOutputStream out = new FileOutputStream(file);
                    props.setProperty("username", Username.getText());
                    props.setProperty("password", Password.getText());
                    props.setProperty("role", getRoleDatabase.getRole(Username.getText()));
                    props.store(out, null);
                    out.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                closeStage(Login);
            }
        });

        LoginAnnuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeStage(LoginAnnuleren);
            }
        });

    }

    public void closeStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
