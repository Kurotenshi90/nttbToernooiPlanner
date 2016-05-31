package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class KnockoutDeeltoernooi extends Deeltoernooi{
    private ArrayList<Bracket> brackets;
    private ArrayList<Deelnemer> deelnemers;

    public KnockoutDeeltoernooi(int maxAantalSpelers, int deeltoernooinr, LocalDateTime beginTijd, double prijs, String spelvorm, boolean gesloten) {
        super(maxAantalSpelers, deeltoernooinr, beginTijd, prijs, spelvorm, gesloten);
        brackets = new ArrayList<>();
        deelnemers = new ArrayList<>();
    }

    public ArrayList<Bracket> getBrackets() {
        return brackets;
    }

    public void setBrackets(ArrayList<Bracket> brackets) {
        this.brackets = brackets;
    }

    @Override
    public ArrayList<Deelnemer> getDeelnemers() {
        return deelnemers;
    }

    @Override
    public void setDeelnemers(ArrayList<Deelnemer> deelnemers) {
        this.deelnemers = deelnemers;
    }
}
