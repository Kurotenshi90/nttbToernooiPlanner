package presentation.controllers;

import domain.Deeltoernooi;
import domain.Klasse;
import domain.Toernooi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.models.DeeltoernooiSelecterenModel;
import presentation.models.LadderInplannenModel;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by dirk on 26-5-2016.
 */
public class DeeltoernooiSelecterenController implements Initializable{
    private DeeltoernooiSelecterenModel deeltoernooiSelecterenModel;

    @FXML private TableView<Deeltoernooi> Deeltoernooi;
    @FXML private TableColumn<Deeltoernooi, String> DeeltoernooiSpelvorm;
    @FXML private TableColumn<Deeltoernooi, String> DeeltoernooiMaxSpelers;
    @FXML private TableColumn<Deeltoernooi, String> DeeltoernooiPrijs;
    @FXML private TableColumn<Deeltoernooi, String> DeeltoernooiTijd;

    @FXML private TableView<Klasse> Klasse;
    @FXML private TableColumn<Klasse, String> KlasseLeeftijd;
    @FXML private TableColumn<Klasse, String> KlasseLicentie;

    @FXML private TableView<Toernooi> ToernooiInfoTable;
    @FXML private TableColumn<Toernooi, String> ToernooiNaamInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiStartdatumInfo;
    @FXML private TableColumn<Toernooi, Date> ToernooiEinddatumInfo;

    @FXML private Button DeeltoernooiInplannen;
    @FXML private Button Annuleren;
    @FXML private Button OverzichtPoule;

    public DeeltoernooiSelecterenController(int toernooi) {
        this.deeltoernooiSelecterenModel = new DeeltoernooiSelecterenModel(toernooi);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewToernooiInfo();
        initializeTableViewDeeltoernooiOverzicht();
        initializeTableVIewKlasse();
        alleButtons();

        if(deeltoernooiSelecterenModel.getToernooi().getToernooisoort().equals("Ladder")){
            DeeltoernooiInplannen.setText("Deeltoernooi Starten");
        }

        OverzichtPoule.setVisible(true);

        Deeltoernooi.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    Klasse.getItems().setAll(Deeltoernooi.getSelectionModel().getSelectedItem().getKlasses());
                }
            }
        });

        if(deeltoernooiSelecterenModel.getToernooi().getToernooisoort().equals("Knockout")){
            OverzichtPoule.setVisible(false);
        }
    }

    private void initializeTableViewToernooiInfo(){
        ToernooiNaamInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, String>("naam"));
        ToernooiStartdatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("begindatum"));
        ToernooiEinddatumInfo.setCellValueFactory(new PropertyValueFactory<Toernooi, Date>("einddatum"));

        ToernooiInfoTable.getItems().setAll(deeltoernooiSelecterenModel.getToernooi());
    }

    private void initializeTableViewDeeltoernooiOverzicht(){
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        DeeltoernooiSpelvorm.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("Spelvorm"));
        DeeltoernooiMaxSpelers.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("maxAantalSpelers"));
        DeeltoernooiPrijs.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("Prijs"));
        //DeeltoernooiTijd.setCellValueFactory(new PropertyValueFactory<Deeltoernooi, String>("Tijd"));

        Deeltoernooi.getItems().setAll(deeltoernooiSelecterenModel.getDeeltoernoois());
    }

    private void initializeTableVIewKlasse(){
        KlasseLeeftijd.setCellValueFactory(new PropertyValueFactory<Klasse, String>("klassenaam"));
        KlasseLicentie.setCellValueFactory(new PropertyValueFactory<Klasse, String>("licentietype"));
    }

    private void alleButtons(){
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
        DeeltoernooiInplannen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                if(deeltoernooi != null) {
                    FXMLLoader loader= null;
                    if(deeltoernooiSelecterenModel.getToernooi().getToernooisoort().equals("Poule")){
                        if (deeltoernooi.getSpelvorm().equals("Enkel")) {

                            loader = new FXMLLoader(getClass().getClassLoader().getResource("views/PouleInplannen.fxml"));
                            PouleInplannenController controller = new PouleInplannenController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                            loader.setController(controller);
                        } else if(deeltoernooi.getSpelvorm().equals("Dubbel")){
                            loader = new FXMLLoader(getClass().getClassLoader().getResource("views/PouleInplannenDubbel.fxml"));
                            PouleInplannenDubbelController controller = new PouleInplannenDubbelController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                            loader.setController(controller);

                        }
                    } else if(deeltoernooiSelecterenModel.getToernooi().getToernooisoort().equals("Knockout")) {
                        if(deeltoernooi.getSpelvorm().equals("Enkel")) {
                            loader = new FXMLLoader(getClass().getClassLoader().getResource("views/KnockoutInplannen.fxml"));
                            KnockoutInplannenController controller = new KnockoutInplannenController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                            loader.setController(controller);

                        } else if(deeltoernooi.getSpelvorm().equals("Dubbel")){
                            loader = new FXMLLoader(getClass().getClassLoader().getResource("views/KnockoutInplannenDubbel.fxml"));
                            KnockoutInplannenDubbelController controller = new KnockoutInplannenDubbelController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                            loader.setController(controller);

                        }
                        deeltoernooiSelecterenModel.knockoutToernooiAanmaken(deeltoernooi);
                    } else if(deeltoernooiSelecterenModel.getToernooi().getToernooisoort().equals("Ladder")){
                        loader = new FXMLLoader(getClass().getClassLoader().getResource("views/LadderInplannen.fxml"));
                        LadderInplannenController controller = new LadderInplannenController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                        loader.setController(controller);
                    }
                    Stage stage = (Stage) DeeltoernooiInplannen.getScene().getWindow();
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
            }
        });

        OverzichtPoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                if(deeltoernooi != null) {
                    FXMLLoader loader= null;
                    //if(deeltoernooi.getSpelvorm().equals("enkel")) {
                        loader = new FXMLLoader(getClass().getClassLoader().getResource("views/PouleOverzicht.fxml"));
                        PouleOverzichtController controller = new PouleOverzichtController(deeltoernooiSelecterenModel.getToernooi(), deeltoernooi.getDeeltoernooinr());
                        loader.setController(controller);
                    //}
                    Stage stage = (Stage) OverzichtPoule.getScene().getWindow();
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
            }
        });
    }
}
