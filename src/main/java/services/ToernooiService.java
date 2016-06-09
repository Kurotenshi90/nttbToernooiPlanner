package services;

import datasource.DAO.ToernooiDao;
import domain.*;

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

    public void deleteToernooi(int toernooiID) {
        toernooiDao.deleteToernooi(toernooiID);
    }


    public ArrayList<PouleOverzicht> getSpelersInPoule(int deeltoernooinummer) {
        return toernooiDao.getSpelersInPoule(deeltoernooinummer);
    }

    public void deeltoernooiStarten(Deeltoernooi deeltoernooi) {
        toernooiDao.deeltoernooiStarten(deeltoernooi);
    }

    public void planKnockout(int deeltoernooinr, int tewinnenrondes, int aantalDoor){
        toernooiDao.planKnockout(deeltoernooinr, tewinnenrondes, aantalDoor);
    }
}
