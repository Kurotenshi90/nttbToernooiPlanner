package main;

/**
 * Created by Peter-Paul on 29/04/2016.
 */
import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.Toernooi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main extends Application {
    public static final List<Toernooi> storage = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        List<CommisieLidInToernooi> dingetje = new ArrayList<>();
        dingetje.add(new CommisieLidInToernooi("Arie", "Bas"));
        dingetje.get(0).setLeider(true);

        storage.add(new Toernooi("afdelingsKampionschap", "2016-04-12", "2016-04-13", "2016-3-01", new Locatie("Arnhem", "Ruitenberglaan", "26"), 0.20, "hier wordt er betaald",dingetje, null ));


        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Home.fxml"));
        primaryStage.setTitle("Toernooi manager V0.0.0.1");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

