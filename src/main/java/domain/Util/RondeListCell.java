package domain.Util;

import domain.Ronde;
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
public class RondeListCell extends ListCell<Ronde> {
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
    private Label scoreA;
    private Label scoreB;

    public RondeListCell() {

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
        scoreA.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        scoreB.getStyleClass().add(CACHE_LIST_NAME_CLASS);

    }

    private void configureDifficultyTerrain() {
        //wedstrijdDatum.getStyleClass().add(CACHE_LIST_DT_CLASS);
    }


    @Override
    public void updateItem(Ronde cache, boolean empty) {
        super.updateItem(cache, empty);
        if (empty) {
            clearContent();
        } else {
            configureCell();




            setGrid();

            addContent(cache);

        }
    }

    private void setGrid() {
        grid.add(teamA, 0, 0);

        grid.add(vs, 1, 0, 1,3);
        grid.add(scoreA, 0,1);
        grid.add(teamB, 2, 0);
        grid.add(scoreB,2, 1);

    }

    private void configureCell() {
        grid  = new GridPane();
        vs = new Label();
        teamA = new Label();
        teamB = new Label();
        scoreA = new Label();
        scoreB = new Label();
        configureIcon();
        configureName();
        configureGrid();
        configureDifficultyTerrain();
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    private void addContent(Ronde cache) {
        setText(null);

        teamA.setText("Team A:");
        scoreA.setText(cache.getScoreA()+"");
        vs.setText("VS");
        teamB.setText("Team B:");
        scoreB.setText(cache.getScoreB()+"");



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
