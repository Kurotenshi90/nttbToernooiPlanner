package services;

import datasource.DAO.CommissieLedenDao;
import domain.NieuwToernooiCommissieLeden;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class CommissieService {
    CommissieLedenDao commissieLedenDao;

    public CommissieService() {
        commissieLedenDao = new CommissieLedenDao();
    }

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        return commissieLedenDao.getNieuwToernooiCommissieLeden();
    }
}
