package services;

import datasource.DAO.DeelnemerDao;
import domain.Deelnemer;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeelnemerService {
    private DeelnemerDao deelnemerDao;

    public DeelnemerService() {
        deelnemerDao = new DeelnemerDao();
    }

    public void saveDeelnemers(ArrayList<Deelnemer> deelnemers) {
        deelnemerDao.saveDeelnemers(deelnemers);
    }

    public ArrayList<Deelnemer> getDeelnemersOfDeeltoernooi(int toernooiID){
        return deelnemerDao.getDeelnemersOfDeeltoernooi(toernooiID);
    }
}
