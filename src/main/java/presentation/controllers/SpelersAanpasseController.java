package presentation.controllers;

import domain.Deelnemer;
import domain.Deeltoernooi;
import domain.Klasse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.models.SpelersInschrijvenModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by donnyolijslager on 25-05-16.
 */
public class SpelersAanpasseController implements Initializable {
    private int toernooiID;
    private SpelersInschrijvenModel spelersAanpassenModel;

    @FXML Button Annuleren;
    @FXML Button Opslaan;
    @FXML Button DeelnemerToevoegen;
    @FXML Button DeelnemerVerwijderen;
    @FXML ChoiceBox<domain.Vereniging> Vereniging;
    @FXML Button VerenigingToevoegen;


    @FXML TextField Toernooinaam;
    @FXML TextField Begindatum;
    @FXML TextField Einddatum;
    @FXML TextField Inschrijfdatum;
    @FXML TextField Spelvorm;

    @FXML TextField Voornaam;
    @FXML TextField Achternaam;
    @FXML TextField Bondsnummer;
    @FXML ChoiceBox Geslacht;
    @FXML ComboBox<domain.Deeltoernooi> Deeltoernooi;
    @FXML ChoiceBox<Klasse> Licentie;

    @FXML TextField VoornaamPartner;
    @FXML TextField AchternaamPartner;
    @FXML TextField BondsnummerPartner;
    @FXML ChoiceBox GeslachtPartner;
    @FXML TextField DeeltoernooiPartner;
    @FXML ChoiceBox<Klasse> LicentiePartner;

    @FXML Label PartnerLable;
    @FXML Label VoornaamPartnerLable;
    @FXML Label AchternaamPartnerLable;
    @FXML Label BondsnummerPartnerLable;
    @FXML Label GeslachtPartnerLable;
    @FXML Label DeeltoernooiPartnerLable;
    @FXML Label LicentiePartnerLable;

    @FXML TableView<Deelnemer> Deelnemers;
    @FXML TableColumn<Deelnemer, String> VoornaamDeelnemer;
    @FXML TableColumn<Deelnemer, String> AchternaamDeelnemer;
    @FXML TableColumn<Deelnemer, String> BondsnummerDeelnemer;
    @FXML TableColumn<Deelnemer, String> GeslachtDeelnemer;
    @FXML TableColumn<Deelnemer, String> DeeltoernooiDeelnemer;
    @FXML TableColumn<Deelnemer, String> LicentieDeelnemer;
    @FXML TableColumn<Deelnemer, String> BondsnummerPartnerDeelnemer;


    public SpelersAanpasseController(int toernooiID){
        this.toernooiID = toernooiID;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spelersAanpassenModel = new SpelersInschrijvenModel();
        spelersAanpassenModel.getAllVerenigings();
        spelersAanpassenModel.getToernooi(toernooiID);
        setPartnerInvis();
        initializeToernooiGegevens();
        initializeGeslacht();
        initializeDeeltoernooi();
        initializeTableViewDeelnemers();
        initializeButtons();
        actionOnSelectedDeeltoernooi();
        initializeVereniging();
        setTableViewDeelnemers();
    }

    private void setTableViewDeelnemers() {
        spelersAanpassenModel.getAllDeelnemersOnToernooi(toernooiID);
        Deelnemers.getItems().setAll(spelersAanpassenModel.getToegevoegdeDeelnemers());
    }

    private void refreshTextfields() {
        setPartnerInvis();
        Voornaam.setText("");
        Achternaam.setText("");
        Bondsnummer.setText("");
        initializeGeslacht();
        VoornaamPartner.setText("");
        AchternaamPartner.setText("");
        BondsnummerPartner.setText("");
    }


    private void initializeToernooiGegevens() {
        Toernooinaam.setEditable(false);
        Toernooinaam.setText(spelersAanpassenModel.getToernooi().getNaam());
        Spelvorm.setEditable(false);
        Spelvorm.setText(spelersAanpassenModel.getToernooi().getToernooisoort());
        Begindatum.setEditable(false);
        Begindatum.setText(spelersAanpassenModel.getToernooi().getBegindatum().toString());
        Inschrijfdatum.setEditable(false);
        Inschrijfdatum.setText(spelersAanpassenModel.getToernooi().getInschrijfdatum().toString());
        Einddatum.setEditable(false);
        Einddatum.setText(spelersAanpassenModel.getToernooi().getEinddatum().toString());
    }

    private void setPartnerInvis() {
        VoornaamPartner.setVisible(false);
        AchternaamPartner.setVisible(false);
        BondsnummerPartner.setVisible(false);
        GeslachtPartner.setVisible(false);
        DeeltoernooiPartner.setVisible(false);
        LicentiePartner.setVisible(false);
        PartnerLable.setVisible(false);
        VoornaamPartnerLable.setVisible(false);
        AchternaamPartnerLable.setVisible(false);
        BondsnummerPartnerLable.setVisible(false);
        GeslachtPartnerLable.setVisible(false);
        DeeltoernooiPartnerLable.setVisible(false);
        LicentiePartnerLable.setVisible(false);
    }

    private void setPartnerVis() {
        VoornaamPartner.setVisible(true);
        AchternaamPartner.setVisible(true);
        BondsnummerPartner.setVisible(true);
        GeslachtPartner.setVisible(true);
        DeeltoernooiPartner.setVisible(true);
        LicentiePartner.setVisible(true);
        PartnerLable.setVisible(true);
        VoornaamPartnerLable.setVisible(true);
        AchternaamPartnerLable.setVisible(true);
        BondsnummerPartnerLable.setVisible(true);
        GeslachtPartnerLable.setVisible(true);
        DeeltoernooiPartnerLable.setVisible(true);
        LicentiePartnerLable.setVisible(true);
    }

    private void goToHome(Button button) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/InschrijfToernooi.fxml"));
        ToernooiOverzichtDeelnemerToevoegenController controller = new ToernooiOverzichtDeelnemerToevoegenController();
        loader.setController(controller);
        Stage stage = (Stage) button.getScene().getWindow();
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

//    private SpelersInschrijvenController getController() {
//        return this;
//    }

    public void loadVerenigingen() {
        spelersAanpassenModel.getAllVerenigings();
        initializeVereniging();
    }

    private void initializeGeslacht() {
        ArrayList<String> geslacht = new ArrayList<>();
        geslacht.add("M");
        geslacht.add("V");
        Geslacht.getItems().setAll(geslacht);
        GeslachtPartner.getItems().setAll(geslacht);
    }

    private void initializeTableViewDeelnemers() {
        VoornaamDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("voornaam"));
        AchternaamDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("achternaam"));
        BondsnummerDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnr"));
        GeslachtDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("geslacht"));
        DeeltoernooiDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("deeltoernooi"));
        LicentieDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("licentie"));
        BondsnummerPartnerDeelnemer.setCellValueFactory(new PropertyValueFactory<Deelnemer, String>("bondsnrPartner"));
    }

    private void initializeDeeltoernooi() {
        Deeltoernooi.getItems().setAll(spelersAanpassenModel.getToernooi().getDeeltoernoois());
    }

    private void initializeVereniging() {
        Vereniging.getItems().setAll(spelersAanpassenModel.getVerenigings());
    }

    private void actionOnSelectedDeeltoernooi() {
        Deeltoernooi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                domain.Deeltoernooi deeltoernooi = Deeltoernooi.getSelectionModel().getSelectedItem();
                Licentie.getItems().setAll(deeltoernooi.getKlasses());
                if(deeltoernooi.getSpelvorm().equals("Dubbel")){
                    setPartnerVis();
                    DeeltoernooiPartner.setText(deeltoernooi.toString());
                    DeeltoernooiPartner.setEditable(false);
                    LicentiePartner.getItems().setAll(deeltoernooi.getKlasses());
                } else {
                    setPartnerInvis();
                }
            }
        });
    }

    private void initializeButtons() {
        DeelnemerToevoegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deelnemer deelnemer;
                deelnemer = new Deelnemer(Deeltoernooi.getSelectionModel().getSelectedItem().getDeeltoernooinr(),
                        Voornaam.getText(), Achternaam.getText(), Integer.parseInt(Bondsnummer.getText()),
                        Geslacht.getValue().toString(), Licentie.getSelectionModel().getSelectedItem().getLicentietype(), 0,
                        Vereniging.getSelectionModel().getSelectedItem().getVerenigingnr(), Deeltoernooi.getSelectionModel().getSelectedItem().toString());
                Deelnemer partner;
                if (Deeltoernooi.getSelectionModel().getSelectedItem().getSpelvorm().equals("Dubbel")){
                    partner = new Deelnemer(Deeltoernooi.getSelectionModel().getSelectedItem().getDeeltoernooinr(),
                            VoornaamPartner.getText(), AchternaamPartner.getText(), Integer.parseInt(BondsnummerPartner.getText()),
                            GeslachtPartner.getValue().toString(), LicentiePartner.getSelectionModel().getSelectedItem().getLicentietype(), deelnemer.getBondsnr(),
                            Vereniging.getSelectionModel().getSelectedItem().getVerenigingnr(), Deeltoernooi.getSelectionModel().getSelectedItem().toString());
                    deelnemer.setBondsnrPartner(partner.getBondsnr());
                    spelersAanpassenModel.addDeelnemer(partner);
                }
                spelersAanpassenModel.addDeelnemer(deelnemer);
                Deelnemers.getItems().setAll(spelersAanpassenModel.getToegevoegdeDeelnemers());
                refreshTextfields();
            }
        });

        Annuleren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goToHome(Annuleren);
            }
        });

        Opslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                spelersAanpassenModel.saveDeelnemers();
                goToHome(Opslaan);
            }
        });

//        VerenigingToevoegen.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/VerenigingToevoegen.fxml"));
//                VerenigingToevoegenController controller = new VerenigingToevoegenController(getController());
//                loader.setController(controller);
//                Parent root = null;
//                try {
//                    root = loader.load();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Stage stage = new Stage();
//                Scene scene = new Scene(root, 480, 320);
//                stage.setScene(scene);
//                stage.initOwner(VerenigingToevoegen.getScene().getWindow());
//                stage.initModality(Modality.WINDOW_MODAL);
//                stage.show();
//            }
//        });

        DeelnemerVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Deelnemer deelnemer = Deelnemers.getSelectionModel().getSelectedItem();
                if(deelnemer != null){
                    spelersAanpassenModel.deleteDeelnemer(deelnemer);
                    Deelnemers.getItems().setAll(spelersAanpassenModel.getToegevoegdeDeelnemers());
                }
            }
        });
    }

}
