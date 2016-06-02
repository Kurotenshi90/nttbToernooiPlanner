package presentation.models;

import domain.Deeltoernooi;
import domain.PlanningWedstrijd;
import domain.Tafel;
import domain.Wedstrijd;
import services.DeeltoernooiService;
import services.WedstrijdenService;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenInplannenModel {
    private int toernooiId;
    private WedstrijdenService wedstrijdenService;
    private DeeltoernooiService deeltoernooiService;
    private PlanningWedstrijd planningWedstrijd;
    private ArrayList<Deeltoernooi> deeltoernoois;

    public WedstrijdenInplannenModel(int toernooiId) {
        this.toernooiId = toernooiId;
        wedstrijdenService = new WedstrijdenService();
        deeltoernooiService = new DeeltoernooiService();
        deeltoernoois = deeltoernooiService.getDeeltoernooi(toernooiId);
        deeltoernoois.add(new Deeltoernooi(0, 0, LocalDateTime.now(), 0, "niks", true));
        getPlanningwedstrijd(toernooiId);
    }

    public void getPlanningwedstrijd(int toernooiId){
        planningWedstrijd = wedstrijdenService.getWedstrijden(toernooiId);
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

    public ArrayList<Wedstrijd> getNietGeplandeWedstrijden() {
        ArrayList<Wedstrijd> nietGeplandeWedstrijden = new ArrayList<>();
        for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
            if(w.getTafel()==null){
                nietGeplandeWedstrijden.add(w);
            }
        }
        return nietGeplandeWedstrijden;
    }

    public ArrayList<Wedstrijd> getNietGeplandeWedstrijdenOnDeeltoernooir(int deeltoernooinr) {
        if(deeltoernooinr == 0){
            return getNietGeplandeWedstrijden();
        }
        ArrayList<Wedstrijd> nietGeplandeWedstrijden = new ArrayList<>();
        for(Wedstrijd w: planningWedstrijd.getWedstrijden()){
            if(w.getTafel()==null && w.getDeeltoernooinr() == deeltoernooinr){
                nietGeplandeWedstrijden.add(w);
            }
        }
        return nietGeplandeWedstrijden;
    }


    public void addTafels(int aantal){
        wedstrijdenService.addTafels(toernooiId, aantal);
    }


    public PlanningWedstrijd getPlanningWedstrijd() {
        return planningWedstrijd;
    }

    public void koppelWedstrijd(Wedstrijd wedstrijd, Tafel tafel){
        wedstrijdenService.koppelTafel(wedstrijd, tafel);
        getPlanningwedstrijd(toernooiId);
    }

    public void onKoppelWedstrijd(Wedstrijd wedstrijd){
        wedstrijdenService.onKoppelTafel(wedstrijd);
        getPlanningwedstrijd(toernooiId);
    }
}
