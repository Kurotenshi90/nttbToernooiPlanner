package presentation.models;

import domain.*;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class KnockoutInplannenModel {
    private Toernooi toernooi;
    private int deeltoernooinr;
    private Deeltoernooi pouledt;

    public KnockoutInplannenModel(Toernooi toernooi, int deeltoernooinr){
        this.toernooi = toernooi;
        this.deeltoernooinr = deeltoernooinr;
        getDeeltoernooi();
    }

    public ArrayList<Deelnemer> getDeelnemers(){
        return pouledt.getDeelnemers();
    }

    public void getDeeltoernooi() {
        for(Deeltoernooi d : toernooi.getDeeltoernoois()){
            if(d.getDeeltoernooinr() == deeltoernooinr) {
                pouledt =  (KnockoutDeeltoernooi)d;
            }
        }
    }
}
