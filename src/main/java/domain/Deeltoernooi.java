package domain;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 17/05/2016.
 */
public class Deeltoernooi {
    private int deeltoernooinr;
    private int maxAantalSpelers;
    private String spelvorm;
    private boolean gesloten;
    private ArrayList<Klasse> klasses;
    private double prijs;
    private LocalDate beginTijd;

    public Deeltoernooi(int maxAantalSpelers, int deeltoernooinr, LocalDate beginTijd, double prijs, String spelvorm, boolean gesloten) {
        this.maxAantalSpelers = maxAantalSpelers;
        this.deeltoernooinr = deeltoernooinr;
        this.beginTijd = beginTijd;
        this.prijs = prijs;
        this.spelvorm = spelvorm;
        this.gesloten = gesloten;
    }

    public Deeltoernooi(int deeltoernooinr, int maxAantalSpelers, String spelvorm, boolean gesloten, ArrayList<Klasse> klasses) {
        klasses = new ArrayList<>();
        this.deeltoernooinr = deeltoernooinr;
        this.maxAantalSpelers = maxAantalSpelers;
        this.spelvorm = spelvorm;
        this.gesloten = gesloten;
        this.klasses = klasses;
    }

    public LocalDate getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(LocalDate beginTijd) {
        this.beginTijd = beginTijd;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public boolean getGesloten() {
        return gesloten;
    }

    public void setGesloten(boolean gesloten) {
        this.gesloten = gesloten;
    }

    public void addKlasse(Klasse klasse){
        klasses.add(klasse);
    }

    public int getMaxAantalSpelers() {
        return maxAantalSpelers;
    }

    public int getDeeltoernooinr() {
        return deeltoernooinr;
    }

    public void setDeeltoernooinr(int deeltoernooinr) {
        this.deeltoernooinr = deeltoernooinr;
    }

    public void setMaxAantalSpelers(int maxAantalSpelers) {
        this.maxAantalSpelers = maxAantalSpelers;
    }

    public String getSpelvorm() {
        return spelvorm;
    }

    public void setSpelvorm(String spelvorm) {
        this.spelvorm = spelvorm;
    }

    public ArrayList<Klasse> getKlasses() {
        return klasses;
    }

    public void setKlasses(ArrayList<Klasse> klasses) {
        this.klasses = klasses;
    }
}
