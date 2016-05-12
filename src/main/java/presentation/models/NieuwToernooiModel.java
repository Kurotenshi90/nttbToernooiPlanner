package presentation.models;

import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.NieuwToernooiCommissieLeden;
import domain.Toernooi;
import services.CommissieService;
import services.LocatieService;
import services.ToernooiService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class NieuwToernooiModel {
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLeden;
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLedenshow;
    private List<CommisieLidInToernooi> addedCommisieLeden = new ArrayList<>();
    private ArrayList<Locatie> locaties = new ArrayList<>();
    private CommissieService commissieService;
    private ToernooiService toernooiService;
    private LocatieService locatieService;




    public NieuwToernooiModel() {
        commissieService = new CommissieService();
        locatieService = new LocatieService();
        toernooiService = new ToernooiService();
        nieuwToernooiCommissieLeden = commissieService.getNieuwToernooiCommissieLeden();
        nieuwToernooiCommissieLedenshow = nieuwToernooiCommissieLeden;
        locaties = locatieService.getLocaties();
    }

    public List<CommisieLidInToernooi> getAddedCommisieLeden() {
        return addedCommisieLeden;
    }

    public void addAddedCommisieLeden(CommisieLidInToernooi addedCommisieLid) {
        this.addedCommisieLeden.add(addedCommisieLid);
        nieuwToernooiCommissieLedenshow = new ArrayList<>();
        System.out.println(nieuwToernooiCommissieLedenshow.size());
        for (NieuwToernooiCommissieLeden ntc : nieuwToernooiCommissieLeden) {
            for(CommisieLidInToernooi ct: this.addedCommisieLeden){
                if(ntc.getLidnr() != ct.getLidnr()) {
                    nieuwToernooiCommissieLedenshow.add(ntc);
                }
            }
        }
        System.out.println(nieuwToernooiCommissieLeden.size());
        System.out.println(nieuwToernooiCommissieLedenshow.size());
    }

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        return nieuwToernooiCommissieLedenshow;
    }

    public ArrayList<Locatie> getLocaties() {
        return locaties;
    }

//    public void saveToernooi(String naam, ){
//
//        toernooiService.saveToernooi(toernooi);
//    }
}
