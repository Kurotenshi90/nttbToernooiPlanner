package presentation.models;

import domain.DeelnemerLadder;
import domain.Toernooi;
import domain.Wedstrijd;
import services.DeelnemerService;
import services.ToernooiService;
import services.WedstrijdenService;

import java.util.ArrayList;

/**
 * Created by dirk on 6-6-2016.
 */
public class LadderInplannenModel {

    private DeelnemerService deelnemerService;
    private WedstrijdenService wedstrijdenService;
    private ArrayList<DeelnemerLadder> deelnemerLadders;
    ArrayList<DeelnemerLadder> deelnemerLaddershow;
    private DeelnemerLadder spelerA;
    private DeelnemerLadder spelerB;
    private int deeltoernooinummer;
    private Toernooi toernooi;
    private ArrayList<Wedstrijd> wedstrijden;

    public LadderInplannenModel(Toernooi toernooi, int deeltoernooinummer) {
        wedstrijdenService = new WedstrijdenService();
        deelnemerService = new DeelnemerService();
        deelnemerLaddershow = new ArrayList<>();
        this.toernooi = toernooi;
        this.deeltoernooinummer = deeltoernooinummer;
        deelnemerLadders = deelnemerService.getLadderDeelnemers(deeltoernooinummer);
        deelnemerLaddershow = deelnemerLadders;
    }

    private void updateWedstrijden(){
       wedstrijden = wedstrijdenService.getWedstrijdenOpDeeltoernooi(deeltoernooinummer);
    }

    public ArrayList<Wedstrijd> getWedstrijden(){
        updateWedstrijden();
        return wedstrijden;
    }

    public Toernooi getToernooi(){
        return toernooi;
    }

    public DeelnemerLadder getSpelerA() {
        return spelerA;
    }

    public void setSpelerA(DeelnemerLadder spelerA) {
        this.spelerA = spelerA;
        makeList(spelerA, spelerB);

    }

    public DeelnemerLadder getSpelerB() {
        return spelerB;
    }

    public void setSpelerB(DeelnemerLadder spelerB) {
        this.spelerB = spelerB;
        makeList(spelerB, spelerA);
    }

    private void makeList(DeelnemerLadder spelerB, DeelnemerLadder spelerA) {
        if(spelerA == null){
            deelnemerLaddershow = new ArrayList<>();
            for(DeelnemerLadder dl: deelnemerLadders){
                if(dl != spelerB){
                    deelnemerLaddershow.add(dl);
                }
            }} else {
            for (DeelnemerLadder dl : deelnemerLadders) {
                if (dl == spelerB) {
                    deelnemerLaddershow.remove(dl);
                }
            }
        }
    }

    public ArrayList<DeelnemerLadder> getDeelnemerLadders() {
        return deelnemerLaddershow;
    }


    public void setDeelnemerLadders(ArrayList<DeelnemerLadder> deelnemerLadders) {
        this.deelnemerLadders = deelnemerLadders;
    }

    public void daagUit(DeelnemerLadder deelnemerLadderA, DeelnemerLadder deelnemerLadderB, int teWinnenRondes) {
        deelnemerService.daagUit(deelnemerLadderA, deelnemerLadderB, teWinnenRondes);
    }
}
