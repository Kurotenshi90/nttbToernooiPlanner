package domain;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 17/05/2016.
 */
public class Deeltoernooi {
    private int maxAantalSpelers;
    private String spelvorm;
    private ArrayList<Klasse> klasses;

    public Deeltoernooi(String spelvorm, int maxAantalSpelers) {
        klasses = new ArrayList<>();
        this.spelvorm = spelvorm;
        this.maxAantalSpelers = maxAantalSpelers;
    }

    public Deeltoernooi(int maxAantalSpelers, String spelvorm, ArrayList<Klasse> klasses) {
        klasses = new ArrayList<>();
        this.maxAantalSpelers = maxAantalSpelers;
        this.spelvorm = spelvorm;
        this.klasses = klasses;
    }

    public void addKlasse(Klasse klasse){
        klasses.add(klasse);
    }

    public int getMaxAantalSpelers() {
        return maxAantalSpelers;
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
