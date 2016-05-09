package main;

import datasource.DAO.DAO;
import datasource.DAO.util.Databaseproperties;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter-Paul on 09/05/2016.
 */
public class Database {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DAO da = new DAO();
        da.connect();
        try {
            ResultSet test = da.getConn().prepareStatement("SELECT * FROM Toernooi").executeQuery();
            while(test.next()){
                System.out.println(test.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        da.disconnect();
    }
}
