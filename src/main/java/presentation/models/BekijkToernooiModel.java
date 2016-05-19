package presentation.models;

import domain.Klasse;
import domain.Toernooi;
import domain.Toernooitype;
import services.KlasseService;
import services.ToernooiService;
import services.ToernooitypeService;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 19-05-16.
 */
public class BekijkToernooiModel {
    private Toernooi toernooi;
    private ToernooiService toernooiService;
    private ToernooitypeService toernooitypeService;
    private ArrayList<Klasse> KlasseAll;
    private KlasseService klasseService;

    public BekijkToernooiModel(){
        klasseService = new KlasseService();
        KlasseAll = klasseService.getKlasses();
        toernooitypeService = new ToernooitypeService();
        toernooiService = new ToernooiService();
    }

    public void getOneToernooi(int toernooiID){
        toernooi = toernooiService.getOneToernooi(toernooiID);
    }

    public Toernooi getToernooi(){
        return toernooi;
    }

    public ArrayList<Toernooitype> getToernooitypes() {
        return toernooitypeService.getToernooitypes();
    }

    public ArrayList<Klasse> getKlasses(ArrayList<Klasse> klasses) {
        return makeKlasseList(klasses);
    }

    private ArrayList<Klasse> makeKlasseList(ArrayList<Klasse> klasses) {
        ArrayList<Klasse> klasseShow = new ArrayList<>();
        for (Klasse ntc: KlasseAll) {
            boolean bestaat = false;
            for(Klasse ct: klasses){
                if(ntc.getKlassenaam().equals(ct.getKlassenaam()) && ntc.getLicentietype().equals(ct.getLicentietype())) {
                    bestaat = true;
                }
            }
            if(bestaat == false){
                klasseShow.add(ntc);
            }
        }
        return klasseShow;
    }
}
