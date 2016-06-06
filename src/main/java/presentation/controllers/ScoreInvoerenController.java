package presentation.controllers;

import domain.Deeltoernooi;
import domain.Ronde;
import domain.Tafel;
import domain.Util.RondeListCell;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.models.ScoresInvoerenModel;
import presentation.models.WedstrijdenInplannenModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class ScoreInvoerenController implements Initializable {
    private Wedstrijd geselecteerdeWedstrijd;
    private int toernooinr;
    @FXML Button Annuleren;

    @FXML ListView Wedstrijden;
    @FXML ListView Wedstrijd;
    @FXML ListView Rondes;

    @FXML Button ScoreInvoeren;
    @FXML Button RondeVerwijderen;

    @FXML TextField ScoreA;
    @FXML TextField ScoreB;


    @FXML ChoiceBox<Deeltoernooi> SelecteerDeeltoernooi;


    private ScoresInvoerenModel scoresInvoerenModel;
    public ScoreInvoerenController(int toernooinummer) {
        toernooinr = toernooinummer;
        scoresInvoerenModel = new ScoresInvoerenModel(toernooinummer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeButtons();
        initializeWedstrijden();
        initializeSelecteerDeeltoernooi();
        initializeWedstrijd();
        initializeRodnes();
    }

    private void initializeRodnes() {
        Rondes.setCellFactory(new Callback<ListView<String>,
                                         ListCell<Ronde>>() {
                                     @Override
                                     public ListCell<Ronde> call(ListView<String> list) {
                                         return new RondeListCell();
                                     }
                                 }
        );
    }

    private void initializeWedstrijd() {
        Wedstrijd.setCellFactory(new Callback<ListView<String>,
                                           ListCell<Wedstrijd>>() {
                                       @Override
                                       public ListCell<Wedstrijd> call(ListView<String> list) {
                                           return new ScoreListCell();
                                       }
                                   }
        );
        Wedstrijd.setMouseTransparent(true);
        Wedstrijd.setFocusTraversable(false);
    }

    private void initializeSelecteerDeeltoernooi() {
        SelecteerDeeltoernooi.getItems().setAll(scoresInvoerenModel.getDeeltoernoois());
    }



    private void initializeWedstrijden() {

        Wedstrijden.setCellFactory(new Callback<ListView<String>,
                                    ListCell<Wedstrijd>>() {
                                @Override
                                public ListCell<Wedstrijd> call(ListView<String> list) {
                                    return new WedstrijdListCell();
                                }
                            }
        );

        Wedstrijden.getItems().setAll(scoresInvoerenModel.getGeplandeWedstrijden());
    }



    private  void initializeButtons(){
        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/InschrijfToernooi.fxml"));
                SelecteerToernooiInplannen controller = new SelecteerToernooiInplannen();
                loader.setController(controller);
                Stage stage = (Stage) Annuleren.getScene().getWindow();
                Parent root = null;
                try {
                    root = loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 1920, 1080);
                stage.setScene(scene);
                stage.show();
            }
        });

        Wedstrijden.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Wedstrijd wedstrijd = ((Wedstrijd) Wedstrijden.getSelectionModel().getSelectedItem());
                if(wedstrijd!=null){
                    geselecteerdeWedstrijd = wedstrijd;
                    Wedstrijd.getItems().setAll(wedstrijd);
                    Rondes.getItems().setAll(wedstrijd.getRondes());
                }
            }
        });





        SelecteerDeeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Wedstrijden.getItems().setAll(scoresInvoerenModel.getGeplandeWedstrijdenOnDeeltoernooir(SelecteerDeeltoernooi.getSelectionModel().getSelectedItem().getDeeltoernooinr()));
            }
        });

        ScoreInvoeren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int scoreA = Integer.parseInt(ScoreA.getText());
                int scoreB = Integer.parseInt(ScoreB.getText());
                Ronde ronde = new Ronde(scoreA, scoreB);
                if(geselecteerdeWedstrijd!=null){
                    geselecteerdeWedstrijd.getRondes().add(ronde);
                    Rondes.getItems().setAll(geselecteerdeWedstrijd.getRondes());
                    scoresInvoerenModel.rondeInvoeren(geselecteerdeWedstrijd.getWedstrijdnr(), ronde);
                    scoresInvoerenModel.resetRondes(toernooinr);
                    Wedstrijden.getItems().setAll(scoresInvoerenModel.getGeplandeWedstrijden());
                }
            }
        });
        RondeVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Ronde ronde = ((Ronde) Rondes.getSelectionModel().getSelectedItem());
                if(geselecteerdeWedstrijd!=null&&ronde!=null){
                    scoresInvoerenModel.rondeVerwijderen(ronde);
                    geselecteerdeWedstrijd.getRondes().remove(ronde);
                    Rondes.getItems().setAll(geselecteerdeWedstrijd.getRondes());
                }
            }
        });
    }
}
