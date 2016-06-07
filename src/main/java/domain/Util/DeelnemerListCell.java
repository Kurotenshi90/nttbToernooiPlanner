package domain.Util;

import domain.Deelnemer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by dirk on 6-6-2016.
 */
public class DeelnemerListCell extends ListCell<Deelnemer> {
    private static final String FONT_AWESOME = "FontAwesome";
    private static final String CACHE_LIST_ICON_CLASS = "cache-list-icon";
    private static final String CACHE_LIST_NAME_CLASS = "cache-list-name";
    private GridPane grid;
    private Label speler;

    public DeelnemerListCell() {

    }

    private void configureGrid(){
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon(){
        speler.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        speler.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName(){
        speler.getStyleClass().add(CACHE_LIST_NAME_CLASS);
    }

    private void configureDifficultyTerrain(){

    }

    public void updateItem(Deelnemer cache, boolean empty){
        super.updateItem(cache, empty);
        if(empty){
            clearContent();
        }
    }//derp

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }
}
