package presentation.models;

import domain.NieuwToernooiCommissieLeden;
import services.CommissieService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class NieuwToernooiModel {
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLeden;
    private CommissieService commissieService;

    public NieuwToernooiModel() {
        commissieService = new CommissieService();
        nieuwToernooiCommissieLeden = commissieService.getNieuwToernooiCommissieLeden();
    }

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        return nieuwToernooiCommissieLeden;
    }
}
