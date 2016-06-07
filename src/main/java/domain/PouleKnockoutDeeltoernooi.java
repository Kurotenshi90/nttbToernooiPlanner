package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 06-06-16.
 */
public class PouleKnockoutDeeltoernooi extends Deeltoernooi {
    private ArrayList<Bracket> brackets;
    private ArrayList<Poule> poules;

    public PouleKnockoutDeeltoernooi(int maxAantalSpelers, int deeltoernooinr, LocalDateTime beginTijd, double prijs, String spelvorm, boolean gesloten) {
        super(maxAantalSpelers, deeltoernooinr, beginTijd, prijs, spelvorm, gesloten);
    }

    public ArrayList<Poule> getPoules() {
        return poules;
    }

    public void setPoules(ArrayList<Poule> poules) {
        this.poules = poules;
    }

    public void addPoule(Poule poule) {
        poules.add(poule);
    }

    public ArrayList<Bracket> getBrackets() {
        return brackets;
    }

    public void setBrackets(ArrayList<Bracket> brackets) {
        this.brackets = brackets;
    }
}
