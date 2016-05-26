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

    public DeeltoernooiSelecterenModel(Toernooi toernooi) {
        this.toernooi = toernooi;
    }

    public ArrayList<Deeltoernooi> getDeeltoernoois(){
        return toernooi.getDeeltoernoois();
    }
}
