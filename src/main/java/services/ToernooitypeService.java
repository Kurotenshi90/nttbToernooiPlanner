package services;

import datasource.DAO.ToernooitypesDao;
import domain.Toernooitype;

import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class ToernooitypeService {
    private ToernooitypesDao toernooitypesDao;

    public ToernooitypeService() {
        toernooitypesDao = new ToernooitypesDao();
    }

    public ArrayList<Toernooitype> getToernooitypes() {
        return toernooitypesDao.getToernooitypesDao();
    }
}
