package presentation.models;

import domain.Deelnemer;
import domain.Toernooi;
import domain.Vereniging;
import services.DeelnemerService;
import services.ToernooiService;
import services.VerenigingService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class SpelersInschrijvenModel {
    private ToernooiService toernooiService;
    private DeelnemerService deelnemerService;
    private VerenigingService verenigingService;
    private Toernooi toernooi;

    private ArrayList<Deelnemer> toegevoegdeDeelnemers;
    private ArrayList<Vereniging> verenigings;

    public SpelersInschrijvenModel(){
        toegevoegdeDeelnemers = new ArrayList<>();
        deelnemerService = new DeelnemerService();
        toernooiService = new ToernooiService();
        verenigingService = new VerenigingService();
    }

    public ArrayList<Deelnemer> getToegevoegdeDeelnemers() {
        return toegevoegdeDeelnemers;
    }

    public void addDeelnemer(Deelnemer deelnemer){
        toegevoegdeDeelnemers.add(deelnemer);
    }

    public void getToernooi(int toernooiID) {
        toernooi = toernooiService.getOneToernooi(toernooiID);
    }

    public Toernooi getToernooi() {
        return toernooi;
    }

    public void saveDeelnemers() {
        deelnemerService.saveDeelnemers(toegevoegdeDeelnemers);
    }

    public void getAllVerenigings() {
        verenigings = verenigingService.getVerenigings();
    }

    public ArrayList<Vereniging> getVerenigings(){
        return verenigings;
    }
}
