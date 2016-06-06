package domain;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 02-06-16.
 */
public class PlanningWedstrijd {
    private ArrayList<Wedstrijd> wedstrijden;
    private ArrayList<Tafel> tafels;

    public PlanningWedstrijd(ArrayList<Wedstrijd> wedstrijden, ArrayList<Tafel> tafels) {
        this.wedstrijden = wedstrijden;
        this.tafels = tafels;
    }

    public ArrayList<Wedstrijd> getWedstrijden() {
        return wedstrijden;
    }

    public void setWedstrijden(ArrayList<Wedstrijd> wedstrijden) {
        this.wedstrijden = wedstrijden;
    }

    public ArrayList<Tafel> getTafels() {
        return tafels;
    }

    public void setTafels(ArrayList<Tafel> tafels) {
        this.tafels = tafels;
    }
}
