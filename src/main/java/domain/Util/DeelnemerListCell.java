package domain.Util;

import domain.Deelnemer;
import domain.DeelnemerLadder;
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
 * Created by dirk on 6-6-2016.
 */
public class DeelnemerListCell extends ListCell<DeelnemerLadder> {
    private static final String FONT_AWESOME = "FontAwesome";
    private static final String CACHE_LIST_ICON_CLASS = "cache-list-icon";
    private static final String CACHE_LIST_NAME_CLASS = "cache-list-name";
    private GridPane grid;
    private Label spelerNaam;
    private Label spelerNaamText;
    private Label rank;
    private Label rankText;
    private Label bondsNr;
    private Label bondsNrText;
    private Label licentie;
    private Label licentieText;

    public DeelnemerListCell() {
    }

    private void configureGrid(){
        grid.setHgap(10);
        grid.setVgap(4);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void configureIcon(){
        spelerNaamText.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        spelerNaamText.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        rankText.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        rankText.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        bondsNrText.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        bondsNrText.getStyleClass().add(CACHE_LIST_ICON_CLASS);
        licentieText.setFont(Font.font(FONT_AWESOME, FontWeight.BOLD, 24));
        licentieText.getStyleClass().add(CACHE_LIST_ICON_CLASS);
    }

    private void configureName(){
        spelerNaam.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        rank.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        bondsNr.getStyleClass().add(CACHE_LIST_NAME_CLASS);
        licentie.getStyleClass().add(CACHE_LIST_NAME_CLASS);
    }

    private void configureDifficultyTerrain(){

    }

    public void updateItem(DeelnemerLadder cache, boolean empty) {
        super.updateItem(cache, empty);
        if (empty) {
            clearContent();
        } else {
            configureCell();
            setGrid();
            configureGrid();

            addContent(cache);

        }
    }

    private void setGrid() {
        grid.add(rankText, 0, 0);
        grid.add(rank, 0, 1);

        grid.add(spelerNaamText, 1,0);
        grid.add(spelerNaam, 1,1);

        grid.add(bondsNrText, 2, 0);
        grid.add(bondsNr, 2, 1);

        grid.add(licentieText, 3,0);
        grid.add(licentie, 3,1);

    }

    private void configureCell() {
        grid  = new GridPane();
        rankText = new Label();
        rank = new Label();
        spelerNaamText = new Label();
        spelerNaam = new Label();
        bondsNrText = new Label();
        bondsNr = new Label();
        licentieText = new Label();
        licentie = new Label();
        configureIcon();
        configureName();
        configureGrid();
        configureDifficultyTerrain();
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    private void addContent(DeelnemerLadder cache) {
        setText(null);
        rankText.setText("Rank");
        rank.setText(cache.getRank() + "");
        spelerNaamText.setText("Naam");
        spelerNaam.setText(cache.getVoornaam() + " " + cache.getAchternaam());
        bondsNrText.setText("Bondsnr");
        bondsNr.setText(cache.getBondsnr() + "");
        licentieText.setText("Licentie");
        licentie.setText(cache.getLicentie());



        //setStyleClassDependingOnFoundState(cache);
        setGraphic(grid);
    }

}
