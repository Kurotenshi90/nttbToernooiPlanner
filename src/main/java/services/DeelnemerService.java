package services;

import datasource.DAO.DeelnemerDao;
import domain.Deelnemer;
import domain.DeelnemerLadder;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeelnemerService {
    private DeelnemerDao deelnemerDao;

    public DeelnemerService() {
        deelnemerDao = new DeelnemerDao();
    }

    public void saveDeelnemers(ArrayList<Deelnemer> deelnemers, int toernooiId) {
        deelnemerDao.saveDeelnemers(deelnemers, toernooiId);
    }

    public ArrayList<Deelnemer> getDeelnemersOfDeeltoernooi(int toernooiID){
        return deelnemerDao.getDeelnemersOfDeeltoernooi(toernooiID);
    }

    public ArrayList<DeelnemerLadder> getLadderDeelnemers(int deeltoernooinr){
        return deelnemerDao.getLadderDeelnemers(deeltoernooinr);
    }

    public void daagUit(DeelnemerLadder deelnemerLadderA, DeelnemerLadder deelnemerLadderB, int teWinnenRondes) {
        deelnemerDao.daagUit(deelnemerLadderA, deelnemerLadderB, teWinnenRondes);
    }
}
