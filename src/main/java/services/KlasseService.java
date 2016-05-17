package services;

import datasource.DAO.KlasseDao;
import domain.Klasse;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class KlasseService {
    KlasseDao klasseDao;

    public KlasseService() {
        klasseDao = new KlasseDao();
    }

    public ArrayList<Klasse> getKlasses() {
        return klasseDao.getKlasses();
    }
}
