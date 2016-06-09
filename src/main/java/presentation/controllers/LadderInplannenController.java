package presentation.controllers;

import domain.Deelnemer;
import domain.DeelnemerLadder;
import domain.Toernooi;
import domain.Util.DeelnemerListCell;
import domain.Util.ScoreListCell;
import domain.Util.WedstrijdListCell;
import domain.Wedstrijd;
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
import javafx.scene.control.TextField;
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
    @FXML ListView Deelnemers;
    @FXML ListView SpelerA;
    @FXML ListView SpelerB;

    @FXML Button Terug;
    @FXML Button DaagUit;
    @FXML Button AddSpelerA;
    @FXML Button AddSpelerB;
    @FXML Button RemoveSpelerA;
    @FXML Button RemoveSpelerB;
    @FXML TextField TeWinnenRondes;
    @FXML ListView Wedstrijden;

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
        initializeListviews();
        initializeDeelnemers();
        initializeWedstrijden();
    }

    private void initializeWedstrijden() {
        Wedstrijden.setCellFactory(new Callback<ListView<String>,
                ListCell<Wedstrijd>>() {
            @Override
            public ListCell<Wedstrijd> call(ListView<String> list) {
                return new WedstrijdListCell();
            }
        });
        Wedstrijden.getItems().setAll(ladderInplannenModel.getWedstrijden());
    }


    private void initializeDeelnemers() {
        Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
    }

    private void initializeListviews() {
        Deelnemers.setCellFactory(new Callback<ListView<String>,
                                         ListCell<DeelnemerLadder>>() {
                                     @Override
                                     public ListCell<DeelnemerLadder> call(ListView<String> list) {
                                         return new DeelnemerListCell();
                                     }
                                 }
        );
        SpelerA.setCellFactory(new Callback<ListView<String>,
                                          ListCell<DeelnemerLadder>>() {
                                      @Override
                                      public ListCell<DeelnemerLadder> call(ListView<String> list) {
                                          return new DeelnemerListCell();
                                      }
                                  }
        );
        SpelerB.setCellFactory(new Callback<ListView<String>,
                                          ListCell<DeelnemerLadder>>() {
                                      @Override
                                      public ListCell<DeelnemerLadder> call(ListView<String> list) {
                                          return new DeelnemerListCell();
                                      }
                                  }
        );
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

        AddSpelerA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeelnemerLadder deelnemerLadder = ((DeelnemerLadder) Deelnemers.getSelectionModel().getSelectedItem());
                if(deelnemerLadder !=null && SpelerA.getItems().size()<1){
                    ladderInplannenModel.setSpelerA(deelnemerLadder);
                    SpelerA.getItems().setAll(deelnemerLadder);
                    Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
                }
            }
        });

        AddSpelerB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeelnemerLadder deelnemerLadder = ((DeelnemerLadder) Deelnemers.getSelectionModel().getSelectedItem());
                if(deelnemerLadder !=null && SpelerB.getItems().size()<1){
                    ladderInplannenModel.setSpelerB(deelnemerLadder);
                    SpelerB.getItems().setAll(deelnemerLadder);
                    Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
                }
            }
        });

        RemoveSpelerA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeelnemerLadder deelnemerLadder = ((DeelnemerLadder) SpelerA.getItems().get(0));
                if(deelnemerLadder !=null){
                    System.out.println(deelnemerLadder);
                    ladderInplannenModel.setSpelerA(null);
                    SpelerA.getItems().clear();
                    Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
                }
            }
        });
        RemoveSpelerB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeelnemerLadder deelnemerLadder = ((DeelnemerLadder) SpelerB.getItems().get(0));
                if(deelnemerLadder !=null){
                    System.out.println(deelnemerLadder);
                    ladderInplannenModel.setSpelerB(null);
                    SpelerB.getItems().clear();
                    Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
                }
            }
        });
        DaagUit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeelnemerLadder deelnemerLadderA = ((DeelnemerLadder) SpelerB.getItems().get(0));
                DeelnemerLadder deelnemerLadderB = ((DeelnemerLadder) SpelerA.getItems().get(0));
                String teWinnenrondes = TeWinnenRondes.getText();
                if(deelnemerLadderA !=null && deelnemerLadderB !=null && teWinnenrondes !=null){
                    System.out.println(deelnemerLadderA.getDeelnemerID());
                    System.out.println(deelnemerLadderB.getDeelnemerID());
                    ladderInplannenModel.daagUit(deelnemerLadderA, deelnemerLadderB,Integer.parseInt(teWinnenrondes) );
                    ladderInplannenModel.setSpelerB(null);
                    ladderInplannenModel.setSpelerA(null);
                    SpelerB.getItems().clear();
                    SpelerA.getItems().clear();
                    TeWinnenRondes.setText("");
                    Deelnemers.getItems().setAll(ladderInplannenModel.getDeelnemerLadders());
                    Wedstrijden.getItems().setAll(ladderInplannenModel.getWedstrijden());
                }
            }
        });
    }


}
