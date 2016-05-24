package services;

import datasource.DAO.DeelnemerDao;
import datasource.DAO.DeeltoernooiDao;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeeltoernooiService {
    private DeeltoernooiDao deeltoernooiDao;

    public DeeltoernooiService() {
        deeltoernooiDao = new DeeltoernooiDao();
    }
}
