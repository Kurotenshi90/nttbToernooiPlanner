package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 26-05-16.
 */
public class PouleDeeltoernooi extends Deeltoernooi {
    private ArrayList<Poule> poules;
    private ArrayList<Deelnemer> deelnemers;


    public PouleDeeltoernooi(int maxAantalSpelers, int deeltoernooinr, LocalDateTime beginTijd, double prijs, String spelvorm, boolean gesloten) {
        super(maxAantalSpelers, deeltoernooinr, beginTijd, prijs, spelvorm, gesloten);
        this.poules = new ArrayList<Poule>();
        this.deelnemers = new ArrayList<Deelnemer>();
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

    public void addDeelnemer(Deelnemer deelnemer){
        deelnemers.add(deelnemer);
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
