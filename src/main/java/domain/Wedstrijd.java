package domain;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 31/05/2016.
 */
public class Wedstrijd {
    private int wedstrijdnr;
    private ArrayList<Integer> speler1;
    private ArrayList<Integer> speler2;

    public Wedstrijd(int wedstrijdnr, ArrayList<Integer> speler1, ArrayList<Integer> speler2) {
        this.wedstrijdnr = wedstrijdnr;
        this.speler1 = speler1;
        this.speler2 = speler2;
    }

    public int getWedstrijdnr() {
        return wedstrijdnr;
    }

    public void setWedstrijdnr(int wedstrijdnr) {
        this.wedstrijdnr = wedstrijdnr;
    }

    public ArrayList<Integer> getSpeler1() {
        return speler1;
    }

    public void setSpeler1(ArrayList<Integer> speler1) {
        this.speler1 = speler1;
    }

    public ArrayList<Integer> getSpeler2() {
        return speler2;
    }

    public void setSpeler2(ArrayList<Integer> speler2) {
        this.speler2 = speler2;
    }
}
