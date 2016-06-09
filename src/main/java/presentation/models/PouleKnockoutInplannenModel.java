package presentation.models;

import domain.*;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by dirk on 26-5-2016.
 */
public class PouleKnockoutInplannenModel extends PouleInplannenModel{
    private PouleKnockoutDeeltoernooi pouledt;
    private ToernooiService toernooiService;

    public PouleKnockoutInplannenModel(Toernooi toernooi, int deeltoernooinummer) {
        super(toernooi, deeltoernooinummer);
        getDeeltoernooi();
        this.toernooiService = new ToernooiService();
    }

    public ArrayList<Deelnemer> getDeelnemers(){
       return pouledt.getDeelnemers();
    }

    public void getDeeltoernooi() {
        for(Deeltoernooi d : toernooi.getDeeltoernoois()){
            if(d.getDeeltoernooinr() == deeltoernooinummer) {
                pouledt =  (PouleKnockoutDeeltoernooi)d;
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
        for(Deelnemer deelnemer: poule.getDeelnemers()){
            pouledt.getDeelnemers().add(deelnemer);
        }
    }

    public void addDeelnemerPoule(Deelnemer deelnemer, Poule poule){
        ArrayList<Deelnemer> deelnemers = pouledt.getDeelnemers();
        Deelnemer deelnemer1 = null;
        poule.addDeelnemer(deelnemer);
        deelnemers.remove(deelnemer);
        if(deelnemer.getBondsnrPartner() != 0) {
            for (Deelnemer deelnemer2 : deelnemers) {
                if (deelnemer2.getBondsnr() == deelnemer.getBondsnrPartner()) {
                    deelnemer1 = deelnemer2;
                }
            }
            deelnemers.remove(deelnemer1);
            poule.addDeelnemer(deelnemer1);
        }
    }

    public void removeDeelnemerPoule(Deelnemer deelnemer, Poule poule){
        ArrayList<Deelnemer> deelnemers = pouledt.getDeelnemers();
        Deelnemer deelnemer1 = null;
        pouledt.getDeelnemers().add(deelnemer);
        poule.removeDeelnemer(deelnemer);
        if(deelnemer.getBondsnrPartner() != 0) {
            for (Deelnemer deelnemer2 : poule.getDeelnemers()) {
                if (deelnemer2.getBondsnr() == deelnemer.getBondsnrPartner()) {
                    deelnemer1 = deelnemer2;
                    System.out.println(deelnemer2);
                    System.out.println(deelnemer1);
                }
            }
            deelnemers.add(deelnemer1);
            poule.removeDeelnemer(deelnemer1);
        }
    }

    public void saveDeelnemersInPoule(){
        toernooiService.saveToernooiIndeling(pouledt);
        System.out.println("hallo");
    }

    public void planEnSluitDeeltoernooiPlanning(int teWinnenRondes){
        toernooiService.planEnSluitDeeltoernooiPlanning(pouledt, teWinnenRondes);
    }

    public Toernooi getToernooi(){
        return toernooi;
    }
}
