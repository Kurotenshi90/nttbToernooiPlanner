package services;

import datasource.DAO.WedstrijdenDao;
import domain.PlanningWedstrijd;
import domain.Ronde;
import domain.Tafel;
import domain.Wedstrijd;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenService {
    private WedstrijdenDao wedstrijdenDao;

    public WedstrijdenService() {
        this.wedstrijdenDao = new WedstrijdenDao();
    }

    public void addTafels(int toernooinummer, int aantal){
        wedstrijdenDao.addTafels(toernooinummer, aantal);
    }

    public PlanningWedstrijd getWedstrijden(int toernooiID) {
        return wedstrijdenDao.getwedstrijden(toernooiID);
    }

    public void koppelTafel(Wedstrijd wedstrijd, Tafel tafel){
        wedstrijdenDao.koppelTafel(wedstrijd, tafel);
    }
    public void onKoppelTafel(Wedstrijd wedstrijd){
        wedstrijdenDao.onKoppelTafel(wedstrijd);
    }

    public void rondeInvoeren(int wedstrijdnr, Ronde ronde){
        wedstrijdenDao.voerRondeIn(wedstrijdnr, ronde);
    }

    public ArrayList<Ronde> getRondes(int toernooiId){
        return wedstrijdenDao.getRondesOpToernooi(toernooiId);
    }

    public void rondeVerwijderen(Ronde ronde){
        wedstrijdenDao.verwijderRonde(ronde);
    }

    public ArrayList<Wedstrijd> getWedstrijdenOpDeeltoernooi(int deeltoernooinr) {
        return wedstrijdenDao.getWedstrijdenOpDeeltoernooi(deeltoernooinr);
    }

}
