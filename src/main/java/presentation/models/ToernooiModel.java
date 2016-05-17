package presentation.models;

import domain.*;
import services.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class ToernooiModel {
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLeden;
    private ArrayList<NieuwToernooiCommissieLeden> nieuwToernooiCommissieLedenshow;
    private ArrayList<CommisieLidInToernooi> addedCommisieLeden = new ArrayList<>();
    private ArrayList<Locatie> locaties = new ArrayList<>();
    private CommissieService commissieService;
    private ToernooiService toernooiService;
    private LocatieService locatieService;
    private KlasseService klasseService;
    private ToernooitypeService toernooitypeService;
    private Locatie locatie;
    private Toernooi toernooi;

    public ToernooiModel() {
        commissieService = new CommissieService();
        locatieService = new LocatieService();
        toernooiService = new ToernooiService();
        toernooitypeService = new ToernooitypeService();
        klasseService = new KlasseService();
        nieuwToernooiCommissieLeden = commissieService.getNieuwToernooiCommissieLeden();
        nieuwToernooiCommissieLedenshow = nieuwToernooiCommissieLeden;
        loadLocaties();
    }

    public void loadLocaties() {
        locaties = locatieService.getLocaties();
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public List<CommisieLidInToernooi> getAddedCommisieLeden() {
        return addedCommisieLeden;
    }

    public void addAddedCommisieLeden(CommisieLidInToernooi addedCommisieLid) {
        addedCommisieLeden.add(addedCommisieLid);
        makeCommissieledenList();
    }

    private void makeCommissieledenList() {
        nieuwToernooiCommissieLedenshow = new ArrayList<>();
        for (NieuwToernooiCommissieLeden ntc: nieuwToernooiCommissieLeden) {
            boolean bestaat = false;
            for(CommisieLidInToernooi ct: addedCommisieLeden){
                if(ntc.getLidnr() == ct.getLidnr()) {
                    bestaat = true;
                }
            }
            if(bestaat == false){
                nieuwToernooiCommissieLedenshow.add(ntc);
            }
        }
    }

    public void deleteCommissieLid(CommisieLidInToernooi deletedCommissielid){
        for(int i= addedCommisieLeden.size()-1; i>=0; i--){
            if(deletedCommissielid.getLidnr() == addedCommisieLeden.get(i).getLidnr()){
                addedCommisieLeden.remove(i);
                System.out.println("hallo");
            }
            System.out.println("hoi");
        }
        System.out.println("doei");
        makeCommissieledenList();
    }

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        makeCommissieledenList();
        return nieuwToernooiCommissieLedenshow;
    }

    public ArrayList<Locatie> getLocaties() {
        return locaties;
    }

    public void saveToernooi(int id, String naam, Date begindatum, Date einddatum, Date inschrijfdatum, double prijs, String betalingsinformatie, String toernooisoort){
        Toernooi toernooi = new Toernooi();
        toernooi.setID(id);
        toernooi.setLocatie(locatie);
        toernooi.setNaam(naam);
        toernooi.setBegindatum(begindatum);
        toernooi.setBetalingsinformatie(betalingsinformatie);
        toernooi.setCommisieLidInToernooi(addedCommisieLeden);
        toernooi.setEinddatum(einddatum);
        toernooi.setInschrijfdatum(inschrijfdatum);
        toernooi.setPrijs(prijs);
        toernooi.setToernooisoort(toernooisoort);

        toernooiService.saveToernooi(toernooi);
    }

    public void getOneToernooi(int toernooiID){
        toernooi = toernooiService.getOneToernooi(toernooiID);
        for(CommisieLidInToernooi commisieLidInToernooi: toernooi.getCommisieLidInToernooi()){
            addedCommisieLeden.add(commisieLidInToernooi);
        }
    }

    public Toernooi getToernooi() {
        return toernooi;
    }

    public ArrayList<Toernooitype> getToernooitypes() {
        return toernooitypeService.getToernooitypes();
    }

    public ArrayList<Klasse> getKlasses() {
        return klasseService.getKlasses();
    }
}
