package domain.Util;

import domain.SpelerInWedstrijd;
import domain.Wedstrijd;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 01-06-16.
 */
public class WedstrijdListCell extends ListCell<Wedstrijd> {
    private static final String CACHE_LIST_FOUND_CLASS = "cache-list-found";
    private static final String CACHE_LIST_NOT_FOUND_CLASS = "cache-list-not-found";
    private static final String CACHE_LIST_NAME_CLASS = "cache-list-name";
    private static final String CACHE_LIST_DT_CLASS = "cache-list-dt";
    private static final String CACHE_LIST_ICON_CLASS = "cache-list-icon";
    private static final String FONT_AWESOME = "FontAwesome";

    private GridPane grid;
    private Label wedstrijdDatum;
    private Label wedstrijdnrText;
    private Label wedstrijdnr;
    private Label wedstrijdBestOfText;
    private Label wedstrijdBestOf;
    private Label teamA;
    private Label teamB;
    private Label tafelnrText;
    private Label tafelnr;
    private ArrayList<Label> teamASpelers;
    private ArrayList<Label> teamBSpelers;

    public WedstrijdListCell() {

    }

    private void configureGrid() {
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon() {
        wedstrijdnr.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        wedstrijdnr.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        wedstrijdBestOf.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        wedstrijdBestOf.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName() {
        teamA.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        teamB.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        wedstrijdnrText.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        wedstrijdBestOfText.getStyleClass().add(CACHE_LIST_NAME_CLASS);
    }

    private void configureDifficultyTerrain() {
        wedstrijdDatum.getStyleClass().add(CACHE_LIST_DT_CLASS);
    }


    @Override
    public void updateItem(Wedstrijd cache, boolean empty) {
        super.updateItem(cache, empty);
        if (empty) {
            clearContent();
        } else {
            configureCell(cache);

            for(SpelerInWedstrijd i: cache.getSpeler1()){
                teamASpelers.add(new Label(i.getVoornaam() + " " + i.getAchternaam() + ", " + i.getBondsnr()));
            }
            for(SpelerInWedstrijd i : cache.getSpeler2()){
                teamBSpelers.add(new Label(i.getVoornaam() + " " + i.getAchternaam() + ", " + i.getBondsnr()));
            }


            setGrid(cache);

            addContent(cache);

        }
    }

    private void setGrid(Wedstrijd wedstrijd) {
        grid.add(wedstrijdDatum, 0, 0, 3,1);

        grid.add(wedstrijdnrText, 0, 1, 1, 1);
        grid.add(wedstrijdnr, 0, 2, 1, teamASpelers.size()+teamBSpelers.size()+2);

        grid.add(wedstrijdBestOfText, 1, 1, 1, 1);
        grid.add(wedstrijdBestOf, 1, 2, 1, teamASpelers.size()+teamBSpelers.size()+2);

        grid.add(teamA, 2, 1);

        for(int i = 0; i<teamASpelers.size(); i++){
            grid.add(teamASpelers.get(i),2,i+2);

        }
        grid.add(teamB, 2, teamASpelers.size()+2);

        for(int i = 0; i<teamBSpelers.size(); i++){
            grid.add(teamBSpelers.get(i),2,teamASpelers.size()+i+3);
        }

        if(wedstrijd.getTafel() != null){
            grid.add(tafelnrText,3, 1);
            grid.add(tafelnr, 3, teamASpelers.size()+teamBSpelers.size()+2);
        }


    }

    private void configureCell(Wedstrijd wedstrijd) {
        grid  = new GridPane();
        wedstrijdDatum = new Label();
        wedstrijdnrText = new Label();
        wedstrijdnr = new Label();
        wedstrijdBestOfText = new Label();
        wedstrijdBestOf = new Label();
        teamA = new Label();
        teamB = new Label();
        teamASpelers = new ArrayList<>();
        teamBSpelers = new ArrayList<>();
        if(wedstrijd.getTafel() != null){
            tafelnrText = new Label();
            tafelnr = new Label();
        }

        configureIcon();
        configureName();
        configureGrid();
        configureDifficultyTerrain();
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    private void addContent(Wedstrijd cache) {
        setText(null);
        wedstrijdDatum.setText(cache.getStartTijdDeeltoernooi().toString());
        wedstrijdnrText.setText("Wedstrijdnr:");
        wedstrijdnr.setText(String.valueOf(cache.getWedstrijdnr()));
        wedstrijdBestOfText.setText("Best of:");
        wedstrijdBestOf.setText(String.valueOf(cache.getTeWinnenRondes()));
        teamA.setText("Team A:");
        for(int i = 0; i>teamASpelers.size(); i++){
            teamASpelers.get(i).setText(cache.getSpeler1().get(i).getVoornaam() + " " + cache.getSpeler1().get(i).getAchternaam() + ", " + cache.getSpeler1().get(i).getBondsnr());
        }
        teamB.setText("Team B:");
        for(int i = 0; i>teamBSpelers.size(); i++){
            teamBSpelers.get(i).setText(cache.getSpeler2().get(i).getVoornaam() + " " + cache.getSpeler1().get(i).getAchternaam() + ", " + cache.getSpeler1().get(i).getBondsnr());
        }

        if(cache.getTafel() != null){
            tafelnrText.setText("Tafelnummer:");
            tafelnr.setText(cache.getTafel().getNaam());
        }




        //setStyleClassDependingOnFoundState(cache);
        setGraphic(grid);
    }

//    private void setStyleClassDependingOnFoundState(Wedstrijd cache) {
//        if (CacheUtils.hasUserFoundCache(cache, new Long(3906456))) {
//            addClasses(this, CACHE_LIST_FOUND_CLASS);
//            removeClasses(this, CACHE_LIST_NOT_FOUND_CLASS);
//        } else {
//            addClasses(this, CACHE_LIST_NOT_FOUND_CLASS);
//            removeClasses(this, CACHE_LIST_FOUND_CLASS);
//        }
//    }
}
