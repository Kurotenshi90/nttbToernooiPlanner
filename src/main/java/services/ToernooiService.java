package services;

import datasource.DAO.ToernooiDao;
import domain.HomePageToernooi;
import domain.Toernooi;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 11/05/2016.
 */
public class ToernooiService {
    ToernooiDao toernooiDao;
    public ToernooiService(){
        toernooiDao =  new ToernooiDao();
    }

    public ArrayList<HomePageToernooi> getHomePageToernoois(){
        return toernooiDao.getHomePageToernoois();
    }
    public void saveToernooi(Toernooi toernooi){
        toernooiDao.saveToernooi(toernooi);
    }
}
