package presentation.models;

import domain.Deeltoernooi;
import domain.PlanningWedstrijd;
import domain.Ronde;
import domain.Wedstrijd;
import services.DeeltoernooiService;
import services.WedstrijdenService;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 02-06-16.
 */
public class ScoresInvoerenModel {
    private int toernooiId;
    private WedstrijdenService wedstrijdenService;
    private DeeltoernooiService deeltoernooiService;
    private PlanningWedstrijd planningWedstrijd;
    private ArrayList<Deeltoernooi> deeltoernoois;

    public ScoresInvoerenModel(int toernooiId) {
        this.toernooiId = toernooiId;
        wedstrijdenService = new WedstrijdenService();
        deeltoernooiService = new DeeltoernooiService();
        deeltoernoois = deeltoernooiService.getDeeltoernooi(toernooiId);
        deeltoernoois.add(new Deeltoernooi(0, 0, LocalDateTime.now(), 0, "niks", true));
        getPlanningwedstrijd(toernooiId);
        setRondes(toernooiId);

    }

    public void resetRondes(int toernooiId){
        getPlanningwedstrijd(toernooiId);
        for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
            w.setRondes(new ArrayList<>());
        }
        setRondes(toernooiId);
    }
    public void setRondes(int toernooiId) {
        for(Ronde ronde: getRondesOpToernooi(toernooiId)){
            for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
                if(w.getWedstrijdnr() == ronde.getWedstrijdnr()){
                    w.getRondes().add(ronde);
                }
            }
        }
    }

    public void getPlanningwedstrijd(int toernooiId){
        planningWedstrijd = wedstrijdenService.getWedstrijden(toernooiId);
    }

    public ArrayList<Ronde> getRondesOpToernooi(int toernooiId){
        return wedstrijdenService.getRondes(toernooiId);
    }

    public ArrayList<Deeltoernooi> getDeeltoernoois(){
        return deeltoernoois;
    }

    public ArrayList<Wedstrijd> getGeplandeWedstrijden() {
        ArrayList<Wedstrijd> geplandeWedstrijden = new ArrayList<>();
        for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
            if(w.getTafel()!=null){
                geplandeWedstrijden.add(w);
            }
        }
        return geplandeWedstrijden;
    }

    public ArrayList<Wedstrijd> getGeplandeWedstrijdenOnDeeltoernooir(int deeltoernooinr) {
        if(deeltoernooinr == 0){
            return getGeplandeWedstrijden();
        }
        ArrayList<Wedstrijd> geplandeWedstrijden = new ArrayList<>();
        for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
            if(w.getTafel()!=null && w.getDeeltoernooinr() == deeltoernooinr){
                geplandeWedstrijden.add(w);
            }
        }
        return geplandeWedstrijden;
    }

    public void rondeInvoeren(int wedstrijdnr, Ronde ronde){
        wedstrijdenService.rondeInvoeren(wedstrijdnr, ronde);
    }

    public void rondeVerwijderen(Ronde ronde){
        wedstrijdenService.rondeVerwijderen(ronde);
    }
}
