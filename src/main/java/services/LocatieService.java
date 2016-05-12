package services;

import datasource.DAO.LocatieDao;
import domain.Locatie;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 12-05-16.
 */
public class LocatieService {
    private LocatieDao locatieDao;

    public LocatieService() {
        locatieDao = new LocatieDao();
    }

    public ArrayList<Locatie> getLocaties() {
        return locatieDao.getLocaties();
    }
}
