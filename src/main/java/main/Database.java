package main;

import datasource.DAO.ToernooiDao;
import domain.CommissieLidInToernooi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 09/05/2016.
 */
public class Database {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ToernooiDao da = new ToernooiDao();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        da.connect();
        ArrayList<CommissieLidInToernooi> arrayList = new ArrayList();
//        arrayList.add(new CommissieLidInToernooi("hoi"))
        CommissieLidInToernooi commisieLidInToernooi = new CommissieLidInToernooi();
        commisieLidInToernooi.setLidnr(3);
        commisieLidInToernooi.setLeider(true);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);

        //Toernooi toernooi = new Toernooi("yolo", new Date(), new Date(), new Date(), new Locatie(2,"","",""),5,"hoi","Knockout", arrayList,null);
        //da.saveToernooi(toernooi);
        da.disconnect();
    }
}
