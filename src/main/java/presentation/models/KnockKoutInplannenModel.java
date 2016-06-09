package presentation.models;

import domain.*;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 30-05-16.
 */
public class KnockKoutInplannenModel extends KnockoutInplannenModel{
    private int deeltoernooinr;
    private PouleKnockoutDeeltoernooi brackedt;

    public KnockKoutInplannenModel(Toernooi toernooi, int deeltoernooinr){
        super(toernooi, deeltoernooinr);
        toernooiService = new ToernooiService();
        this.toernooi = toernooi;
        this.deeltoernooinr = deeltoernooinr;
        getDeeltoernooi();

    }
    public Toernooi getToernooi(){
        return toernooi;
    }

    public ArrayList<Deelnemer> getDeelnemers(){
        return brackedt.getDeelnemers();
    }

    public void getDeeltoernooi() {
        for(Deeltoernooi d : toernooi.getDeeltoernoois()){
            if(d.getDeeltoernooinr() == deeltoernooinr) {
                brackedt =  (PouleKnockoutDeeltoernooi)d;
            }
        }
    }

    public ArrayList<Bracket> getBrackets(){
        return brackedt.getBrackets();
    }


    public void addDeelnemerBracket(Deelnemer selectedItem, Bracket selectedItem1, boolean speler1) {
        ArrayList<Deelnemer> deelnemers = brackedt.getDeelnemers();
        Deelnemer deelnemer1 = null;
        if(speler1) {
            selectedItem1.addSpeler1(selectedItem);
        } else {
            selectedItem1.addSpeler2(selectedItem);
        }

        deelnemers.remove(selectedItem);
        if(selectedItem.getBondsnrPartner() != 0) {
            for (Deelnemer deelnemer2 : deelnemers) {
                if (deelnemer2.getBondsnr() == selectedItem.getBondsnrPartner()) {
                    deelnemer1 = deelnemer2;
                }
            }
            deelnemers.remove(deelnemer1);
            if(speler1) {
                selectedItem1.addSpeler1(deelnemer1);
            } else {
                selectedItem1.addSpeler2(deelnemer1);
            }
        }
    }

    public void removeDeelnemerBracket(Deelnemer deelnemer, Bracket bracket, boolean speler1){
        ArrayList<Deelnemer> deelnemers = brackedt.getDeelnemers();
        Deelnemer deelnemer1 = null;
        if(speler1) {
            bracket.deleteSpeler1(deelnemer);
        } else {
            bracket.deleteSpeler2(deelnemer);
        }
        deelnemers.add(deelnemer);
        if (deelnemer.getBondsnrPartner() != 0) {
        if(speler1) {
                for (Deelnemer deelnemer2 : bracket.getSpeler1()) {
                    if (deelnemer2.getBondsnr() == deelnemer.getBondsnrPartner()) {
                        deelnemer1 = deelnemer2;
                    }
                }
                deelnemers.add(deelnemer1);
                bracket.deleteSpeler1(deelnemer1);
            } else {
                    for (Deelnemer deelnemer2 : bracket.getSpeler2()) {
                        if (deelnemer2.getBondsnr() == deelnemer.getBondsnrPartner()) {
                            deelnemer1 = deelnemer2;
                        }
                    }
                    deelnemers.add(deelnemer1);
                    bracket.deleteSpeler1(deelnemer1);
            }
        }
    }

    public void saveDeelnemersInPoule(){
        toernooiService.saveToernooiIndeling(brackedt);
    }
}
