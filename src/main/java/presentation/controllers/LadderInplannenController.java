package presentation.controllers;

import domain.Deelnemer;
import domain.Toernooi;
import domain.Util.DeelnemerListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.models.LadderInplannenModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by dirk on 6-6-2016.
 */
public class LadderInplannenController implements Initializable{
    @FXML ListView<Deelnemer> Deelnemers;
    @FXML ListView<Deelnemer> SpelerA;
    @FXML ListView<Deelnemer> SpelerB;

    @FXML Button Terug;
    @FXML Button DaagUit;
    @FXML Button AddSpelerA;
    @FXML Button AddSpelerB;
    @FXML Button RemoveSpelerA;
    @FXML Button RemoveSpelerB;

    private LadderInplannenModel ladderInplannenModel;
    private int deeltoernooinr;
    private Toernooi toernooi;

    public LadderInplannenController(Toernooi toernooi, int deeltoernooinr) {
        this.toernooi = toernooi;
        this.deeltoernooinr = deeltoernooinr;
        this.ladderInplannenModel = new LadderInplannenModel(toernooi, deeltoernooinr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
    }

    private void initializeButtons(){
        Terug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/DeeltoernooiSelecteren.fxml"));
                DeeltoernooiSelecterenController controller = new DeeltoernooiSelecterenController(ladderInplannenModel.getToernooi().getID());
                loader.setController(controller);
                Stage stage = (Stage) Terug.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    private void initializeListView(){
        SpelerA.setCellFactory(new Callback<ListView<Deelnemer>, ListCell<Deelnemer>>() {
            @Override
            public ListCell<Deelnemer> call(ListView<Deelnemer> param) {
                return new DeelnemerListCell();
            }
        });
    }
}
