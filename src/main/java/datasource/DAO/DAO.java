package datasource.DAO;

import datasource.DAO.util.Databaseproperties;
import datasource.util.DatabaseProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class DAO {
    public static Connection conn;


    public static Databaseproperties databaseproperties;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //databaseproperties = new Databaseproperties();
        connect();
        try {
            ResultSet test = conn.prepareStatement("SELECT * FROM klasse").executeQuery();
            while(test.next()){
                System.out.println(test.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }
    public static void connect(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://groep4.ise.icaprojecten.nl", "toernooimanager", "TM123mt!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static  void disconnect(){
        try {
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
