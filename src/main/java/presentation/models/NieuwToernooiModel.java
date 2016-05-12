package presentation.models;

import domain.NieuwToernooiCommissieLeden;
import domain.Toernooi;
import services.CommissieService;
import services.ToernooiService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class NieuwToernooiModel {
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLeden;
    private CommissieService commissieService;
    private ToernooiService toernooiService;

    public NieuwToernooiModel() {
        commissieService = new CommissieService();
        nieuwToernooiCommissieLeden = commissieService.getNieuwToernooiCommissieLeden();
        toernooiService = new ToernooiService();
    }

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        return nieuwToernooiCommissieLeden;
    }
    public void saveToernooi(Toernooi toernooi){
        toernooiService.saveToernooi(toernooi);
    }
}
