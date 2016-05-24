package services;

import datasource.DAO.VerenigingDao;
import domain.Vereniging;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 24-05-16.
 */
public class VerenigingService {
    private VerenigingDao verenigingDao;

    public VerenigingService() {
        verenigingDao = new VerenigingDao();
    }

    public ArrayList<Vereniging> getVerenigings() {
        return verenigingDao.getAllVerenigingen();
    }

    public void saveVereniging(Vereniging vereniging){
        verenigingDao.saveVereniging(vereniging);
    }
}
