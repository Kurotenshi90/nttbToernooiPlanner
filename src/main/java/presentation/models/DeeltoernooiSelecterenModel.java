package presentation.models;

import domain.Deeltoernooi;
import domain.Toernooi;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 26-05-16.
 */
public class DeeltoernooiSelecterenModel {
    private ToernooiService toernooiService;
    private Toernooi toernooi;

    public DeeltoernooiSelecterenModel(int toernooi) {
        toernooiService = new ToernooiService();
        this.toernooi = toernooiService.getOneToernooi(toernooi);
    }

    public ArrayList<Deeltoernooi> getDeeltoernoois(){
        return toernooi.getDeeltoernoois();
    }
    public Toernooi getToernooi(){
        return toernooi;
    }

    public void knockoutToernooiAanmaken(Deeltoernooi deeltoernooi) {
        toernooiService.knockoutToernooiAanmaken(deeltoernooi);
    }

    public void deeltoernooiStarten(Deeltoernooi deeltoernooi) {
        toernooiService.deeltoernooiStarten(deeltoernooi);
    }

    public void planKnockout(int deeltoernooinr, int tewinnenrondes, int aantaldoor){
        toernooiService.planKnockout(deeltoernooinr, tewinnenrondes, aantaldoor);
    }
}
