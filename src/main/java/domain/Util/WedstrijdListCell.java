package domain.Util;

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

    private GridPane grid = new GridPane();
    private Label wedstrijdnrText = new Label();
    private Label wedstrijdnr = new Label();
    private Label teamA = new Label();
    private Label teamB = new Label();
    private ArrayList<Label> teamASpelers;
    private ArrayList<Label> teamBSpelers;

    public WedstrijdListCell() {
        teamASpelers = new ArrayList<>();
        teamBSpelers = new ArrayList<>();
        configureGrid();
        configureIcon();
        configureName();
        configureDifficultyTerrain();
        addControlsToGrid();
    }

    private void configureGrid() {
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon() {
        wedstrijdnr.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        wedstrijdnr.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName() {
        teamA.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        teamB.getStyleClass().add(CACHE_LIST_NAME_CLASS);
    }

    private void configureDifficultyTerrain() {
     //   dt.getStyleClass().add(CACHE_LIST_DT_CLASS);
    }

    private void addControlsToGrid() {
        grid.add(wedstrijdnrText, 0, 0, 1, 1);
        grid.add(wedstrijdnr, 0, 1, 1, teamASpelers.size()+teamBSpelers.size()+1);
        grid.add(teamA, 1, 0);
        for(int i = 0; i>teamASpelers.size(); i++){
            grid.add(teamASpelers.get(i),1,i+1);
        }
        grid.add(teamB, 1, teamASpelers.size()+1);
        for(int i = 0; i>teamBSpelers.size(); i++){
            grid.add(teamBSpelers.get(i),1,teamASpelers.size()+i+2);
        }
    }

    @Override
    public void updateItem(Wedstrijd cache, boolean empty) {
        super.updateItem(cache, empty);
        if (empty) {
            clearContent();
        } else {
            addContent(cache);
            for(Integer i: cache.getSpeler1()){
                teamASpelers.add(new Label(i.toString()));
            }
            for(Integer i : cache.getSpeler2()){
                teamBSpelers.add(new Label(i.toString()));
            }
        }
        System.out.println("hoi");
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    private void addContent(Wedstrijd cache) {
        setText(null);
        wedstrijdnrText.setText("Wedstrijdnr:");
        wedstrijdnr.setText(String.valueOf(cache.getWedstrijdnr()));
        teamA.setText("Team A:");
        teamB.setText("Team B:");
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
