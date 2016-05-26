package domain;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 26-05-16.
 */
public class Poule {
    private ArrayList<Deelnemer> deelnemers;

    public Poule() {
        deelnemers = new ArrayList<>();
    }

    public ArrayList<Deelnemer> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(ArrayList<Deelnemer> deelnemers) {
        this.deelnemers = deelnemers;
    }
}
