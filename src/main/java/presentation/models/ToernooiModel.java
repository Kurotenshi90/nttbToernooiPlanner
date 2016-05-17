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

    private ArrayList<Deeltoernooi> deeltoernoois = new ArrayList<>();
    private ArrayList<Locatie> locaties = new ArrayList<>();

    private ArrayList<Klasse> KlasseAll;

    private CommissieService commissieService;
    private ToernooiService toernooiService;
    private LocatieService locatieService;
    private KlasseService klasseService;
    private ToernooitypeService toernooitypeService;
    private SpelvormService spelvormService;
    private Locatie locatie;
    private Toernooi toernooi;

    public ToernooiModel() {
        commissieService = new CommissieService();
        locatieService = new LocatieService();
        toernooiService = new ToernooiService();
        toernooitypeService = new ToernooitypeService();
        klasseService = new KlasseService();
        spelvormService = new SpelvormService();
        nieuwToernooiCommissieLeden = commissieService.getNieuwToernooiCommissieLeden();
        nieuwToernooiCommissieLedenshow = nieuwToernooiCommissieLeden;
        KlasseAll = klasseService.getKlasses();
        loadLocaties();
    }
    public void addDeeltoernooi(Deeltoernooi deeltoernooi){
        deeltoernoois.add(deeltoernooi);
    }
    public ArrayList<Deeltoernooi> getDeeltoernoois(){
        return deeltoernoois;
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

    public void deleteCommissieLid(CommisieLidInToernooi deletedCommissielid){
        for(int i= addedCommisieLeden.size()-1; i>=0; i--){
            if(deletedCommissielid.getLidnr() == addedCommisieLeden.get(i).getLidnr()){
                addedCommisieLeden.remove(i);
            }
        }
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
        toernooi.setDeeltoernoois(deeltoernoois);

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



    public ArrayList<Spelvorm> getSpelvormen() {
        return spelvormService.getSpelvormen();
    }
}
