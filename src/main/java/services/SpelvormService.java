package services;

import datasource.DAO.SpelvormDao;
import domain.Spelvorm;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class SpelvormService {
    private SpelvormDao spelvormDao;

    public SpelvormService() {
        spelvormDao = new SpelvormDao();
    }

    public ArrayList<Spelvorm> getSpelvormen() {
        return spelvormDao.getSpelvormen();
    }
}
