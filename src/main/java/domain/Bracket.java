package domain;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class Bracket {
    int bracketnr;
    private ArrayList<Deelnemer> speler1;
    private ArrayList<Deelnemer> speler2;

    public Bracket(int bracketnr){
        speler1 = new ArrayList<>();
        speler2 = new ArrayList<>();
        this.bracketnr = bracketnr;
    }

    public void addSpeler1(Deelnemer deelnemer){
        speler1.add(deelnemer);
    }

    public void addSpeler2(Deelnemer deelnemer){
        speler2.add(deelnemer);
    }

    public void deleteSpeler1(Deelnemer deelnemer){
        speler1.remove(deelnemer);
    }

    public void deleteSpeler2(Deelnemer deelnemer){
        speler2.remove(deelnemer);
    }

    public ArrayList<Deelnemer> getSpeler1() {
        return speler1;
    }

    public void setSpeler1(ArrayList<Deelnemer> speler1) {
        this.speler1 = speler1;
    }

    public ArrayList<Deelnemer> getSpeler2() {
        return speler2;
    }

    public void setSpeler2(ArrayList<Deelnemer> speler2) {
        this.speler2 = speler2;
    }
}
