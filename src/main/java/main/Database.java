package main;

import datasource.DAO.DAO;
import datasource.DAO.ToernooiDao;
import datasource.DAO.util.Databaseproperties;
import domain.CommisieLidInToernooi;
import domain.Locatie;
import domain.Toernooi;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Peter-Paul on 09/05/2016.
 */
public class Database {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ToernooiDao da = new ToernooiDao();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        da.connect();
        ArrayList<CommisieLidInToernooi> arrayList = new ArrayList();
//        arrayList.add(new CommisieLidInToernooi("hoi"))
        CommisieLidInToernooi commisieLidInToernooi = new CommisieLidInToernooi();
        commisieLidInToernooi.setLidnr(3);
        commisieLidInToernooi.setLeider(true);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);
        arrayList.add(commisieLidInToernooi);

        Toernooi toernooi = new Toernooi("yolo", new Date(), new Date(), new Date(), new Locatie(2,"","",""),5,"hoi","Knockout", arrayList,null);
        da.saveToernooi(toernooi);
        da.disconnect();
    }
}
