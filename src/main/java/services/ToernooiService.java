package services;

import datasource.DAO.ToernooiDao;
import domain.Deeltoernooi;
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

    public Toernooi getOneToernooi(int toernooiID){
        return toernooiDao.getOneToernooi(toernooiID);
    }

    public void saveToernooiIndeling(Deeltoernooi deeltoernooi){
        toernooiDao.saveToernooiIndeling(deeltoernooi);
    }

    public void planEnSluitDeeltoernooiPlanning(Deeltoernooi deeltoernooi, int teWinnenRondes){
        toernooiDao.planEnSluitDeeltoernooiPlanning(deeltoernooi, teWinnenRondes);
    }

    public void knockoutToernooiAanmaken(Deeltoernooi deeltoernooi){
        toernooiDao.knockoutToernooiAanmaken(deeltoernooi);
    }
}
