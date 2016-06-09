package presentation.models;

import domain.*;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by dirk on 6-6-2016.
 */
public class PouleOverzichtModel {
    private int deeltoernooinummer;
    private Toernooi toernooi;
    private PouleDeeltoernooi pouledeeltoernooi;
    private ToernooiService toernooiService;
    private ArrayList<PouleOverzicht> pouleOverzicht;

    public PouleOverzichtModel(Toernooi toernooi, int deeltoernooinummer) {
        this.toernooi = toernooi;
        this.deeltoernooinummer = deeltoernooinummer;
        getDeeltoernooi();
        this.toernooiService = new ToernooiService();
        this.pouleOverzicht = new ArrayList<>();
        pouleOverzicht = toernooiService.getSpelersInPoule(deeltoernooinummer);
    }

    public Toernooi getToernooi() {
        return toernooi;
    }

    public void getDeeltoernooi() {
        for(Deeltoernooi d : toernooi.getDeeltoernoois()){
            if(d.getDeeltoernooinr() == deeltoernooinummer) {
                pouledeeltoernooi =  (PouleDeeltoernooi)d;
            }
        }
    }

    public ArrayList<PouleOverzicht> getPouleOverzichts() {
        return pouledeeltoernooi.getPouleOverzichts();
    }

    public ArrayList<PouleOverzicht> getSpelersInPoule(){
        return pouleOverzicht;
    }
}
