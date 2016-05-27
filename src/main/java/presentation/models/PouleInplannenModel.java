package presentation.models;

import domain.*;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by dirk on 26-5-2016.
 */
public class PouleInplannenModel {
    private Toernooi toernooi;
    private int deeltoernooinummer;
    private PouleDeeltoernooi pouledt;
    private ToernooiService toernooiService;

    public PouleInplannenModel(Toernooi toernooi, int deeltoernooinummer) {
        this.toernooi = toernooi;
        this.deeltoernooinummer = deeltoernooinummer;
        getDeeltoernooi();
        this.toernooiService = new ToernooiService();
    }

    public ArrayList<Deelnemer> getDeelnemers(){
       return pouledt.getDeelnemers();
    }

    public void getDeeltoernooi() {
        for(Deeltoernooi d : toernooi.getDeeltoernoois()){
            if(d.getDeeltoernooinr() == deeltoernooinummer) {
                pouledt =  (PouleDeeltoernooi)d;
            }
        }
    }

    public ArrayList<Poule> getPoules(){
        return pouledt.getPoules();
    }

    public void addPoule(){
        pouledt.getPoules().add(new Poule("poule " + (pouledt.getPoules().size() + 1)));
    }

    public void removePoule(Poule poule){
        pouledt.getPoules().remove(poule);
        for(int i = 0; i < pouledt.getPoules().size(); i++){
            pouledt.getPoules().get(i).setNaam("Poule " + (i + 1));
        }
    }

    public void addDeelnemerPoule(Deelnemer deelnemer, Poule poule){
        poule.addDeelnemer(deelnemer);
        pouledt.getDeelnemers().remove(deelnemer);
    }

    public void removeDeelnemerPoule(Deelnemer deelnemer, Poule poule){
        pouledt.getDeelnemers().add(deelnemer);
        poule.removeDeelnemer(deelnemer);
    }

    public void saveDeelnemersInPoule(){
        toernooiService.saveToernooiIndeling(pouledt);
    }

    public void planEnSluitDeeltoernooiPlanning(){
        toernooiService.planEnSluitDeeltoernooiPlanning(pouledt);
    }

    public Toernooi getToernooi(){
        return toernooi;
    }
}
