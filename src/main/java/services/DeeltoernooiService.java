package services;

import datasource.DAO.DeelnemerDao;
import datasource.DAO.DeeltoernooiDao;
import domain.Deelnemer;
import domain.Deeltoernooi;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeeltoernooiService {
    private DeeltoernooiDao deeltoernooiDao;

    public DeeltoernooiService() {
        deeltoernooiDao = new DeeltoernooiDao();
    }

    public ArrayList<Deeltoernooi> getDeeltoernooi(int toernooiId) {
        return deeltoernooiDao.getDeeltoernoois(toernooiId);
    }
}
