package presentation.controllers;

import domain.CommisieLid;
import domain.CommisieLidInToernooi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class NiewToernooiController implements Initializable {
    @FXML Button CommisieLidToevoegen;

    @FXML TableView<CommisieLid> CommisieLeden;
    @FXML TableColumn<CommisieLid, String> CommisieVoornaam;
    @FXML TableColumn<CommisieLid, String> CommisieAchternaam;

    @FXML TableView<CommisieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieVoornaam;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieAchternaam;

    private List<CommisieLidInToernooi> addedCommisieLeden = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
        initializeTableViewCommisieLeden();
        initializeTableViewAddedCommisieLeden();


    }

    private void initializeTableViewAddedCommisieLeden() {
        AddedCommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("voornaam"));
        AddedCommisieAchternaam.setCellValueFactory(new PropertyValueFactory<CommisieLidInToernooi, String>("achternaam"));
        AddedCommisieLeden.getItems().setAll(addedCommisieLeden);
    }

    private void initializeButtons() {
        CommisieLidToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CommisieLid selected = CommisieLeden.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    addedCommisieLeden.add(new CommisieLidInToernooi(selected.getVoornaam(), selected.getAchternaam()));
                    AddedCommisieLeden.getItems().setAll(addedCommisieLeden);
                }
                System.out.println("hoi");
            }
        });
    }

    private void initializeTableViewCommisieLeden() {
        CommisieVoornaam.setCellValueFactory(new PropertyValueFactory<CommisieLid, String>("voornaam"));
        CommisieAchternaam.setCellValueFactory(new PropertyValueFactory<CommisieLid, String>("achternaam"));
        CommisieLeden.getItems().setAll(Main.commisieleden);
    }
}
