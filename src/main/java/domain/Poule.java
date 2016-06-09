package domain;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 26-05-16.
 */
public class Poule {
    private ArrayList<Deelnemer> deelnemers;
    private String naam;

    public Poule(String naam) {
        deelnemers = new ArrayList<>();
        this.naam = naam;
    }

    public ArrayList<Deelnemer> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(ArrayList<Deelnemer> deelnemers) {
        this.deelnemers = deelnemers;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void addDeelnemer(Deelnemer deelnemer){
        deelnemers.add(deelnemer);
    }
    public void removeDeelnemer(Deelnemer deelnemer){
        deelnemers.remove(deelnemer);
    }


}
