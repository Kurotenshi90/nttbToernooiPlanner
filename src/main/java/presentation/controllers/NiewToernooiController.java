package presentation.controllers;

import domain.CommisieLid;
import domain.CommisieLidInToernooi;
import domain.NieuwToernooiCommissieLeden;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import presentation.models.NieuwToernooiModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class NiewToernooiController implements Initializable {
    @FXML Button CommisieLidToevoegen;

    @FXML TableView<NieuwToernooiCommissieLeden> CommisieLeden;
    @FXML TableColumn<NieuwToernooiCommissieLeden, String> CommisieVoornaam;

    @FXML TableView<CommisieLidInToernooi> AddedCommisieLeden;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieVoornaam;
    @FXML TableColumn<CommisieLidInToernooi, String> AddedCommisieAchternaam;

    private List<CommisieLidInToernooi> addedCommisieLeden = new ArrayList<>();

    private NieuwToernooiModel nieuwToernooiModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nieuwToernooiModel = new NieuwToernooiModel();
        initializeButtons();
        initializeTableViewCommisieLeden();
        initializeTableViewAddedCommisieLeden();
        System.out.println(nieuwToernooiModel.getNieuwToernooiCommissieLeden().get(1).getNaam());


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
                NieuwToernooiCommissieLeden selected = CommisieLeden.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    //addedCommisieLeden.add(new CommisieLidInToernooi(selected.getNaam()));
                    AddedCommisieLeden.getItems().setAll(addedCommisieLeden);
                }
                System.out.println("hoi");
            }
        });
    }

    private void initializeTableViewCommisieLeden() {
        CommisieVoornaam.setCellValueFactory(new PropertyValueFactory<NieuwToernooiCommissieLeden, String>("naam"));
        CommisieLeden.getItems().setAll(nieuwToernooiModel.getNieuwToernooiCommissieLeden());
    }
}
