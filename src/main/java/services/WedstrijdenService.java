package services;

import datasource.DAO.WedstrijdenDao;
import domain.Tafel;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenService {
    private WedstrijdenDao wedstrijdenDao;

    public WedstrijdenService() {
        this.wedstrijdenDao = new WedstrijdenDao();
    }

    public ArrayList<Tafel> getNietBezetteTafels(int toernooiId){
        return wedstrijdenDao.getNietBezetteTafels(toernooiId);
    }
    public void addTafels(int toernooinummer, int aantal){
        wedstrijdenDao.addTafels(toernooinummer, aantal);
    }
}
