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
public class ScoreListCell extends ListCell<Wedstrijd> {
    private static final String CACHE_LIST_FOUND_CLASS = "cache-list-found";
    private static final String CACHE_LIST_NOT_FOUND_CLASS = "cache-list-not-found";
    private static final String CACHE_LIST_NAME_CLASS = "cache-list-name";
    private static final String CACHE_LIST_DT_CLASS = "cache-list-dt";
    private static final String CACHE_LIST_ICON_CLASS = "cache-list-icon";
    private static final String FONT_AWESOME = "FontAwesome";

    private GridPane grid;
    private Label vs;
    private Label teamA;
    private Label teamB;
    private ArrayList<Label> teamASpelers;
    private ArrayList<Label> teamBSpelers;

    public ScoreListCell() {

    }

    private void configureGrid() {
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon() {
        teamA.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        teamA.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        teamB.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        teamB.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        vs.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        vs.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName() {
        for(Label label: teamASpelers){
            label.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        }
        for(Label label: teamBSpelers){
            label.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        }

    }

    private void configureDifficultyTerrain() {
        //wedstrijdDatum.getStyleClass().add(CACHE_LIST_DT_CLASS);
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
        grid.add(teamA, 0, 0);

        for(int i = 0; i<teamASpelers.size(); i++){
            grid.add(teamASpelers.get(i),0,i+1);

        }
        grid.add(vs, 1, 0, 1,teamASpelers.size()+2);

        grid.add(teamB, 2, 0);

        for(int i = 0; i<teamBSpelers.size(); i++){
            grid.add(teamBSpelers.get(i),2,i+1);
        }

    }

    private void configureCell(Wedstrijd wedstrijd) {
        grid  = new GridPane();
        vs = new Label();
        teamA = new Label();
        teamB = new Label();
        teamASpelers = new ArrayList<>();
        teamBSpelers = new ArrayList<>();

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

        teamA.setText("Team A:");
        for(int i = 0; i>teamASpelers.size(); i++){
            teamASpelers.get(i).setText(cache.getSpeler1().get(i).getVoornaam() + " " + cache.getSpeler1().get(i).getAchternaam() + ", " + cache.getSpeler1().get(i).getBondsnr());
        }
        vs.setText("VS");
        teamB.setText("Team B:");
        for(int i = 0; i>teamBSpelers.size(); i++){
            teamBSpelers.get(i).setText(cache.getSpeler2().get(i).getVoornaam() + " " + cache.getSpeler1().get(i).getAchternaam() + ", " + cache.getSpeler1().get(i).getBondsnr());
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
