package datasource.DAO;

import datasource.DAO.util.Databaseproperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter-Paul on 26/04/2016.
 */
public class DAO {
    public  Connection conn;
    public  Databaseproperties databaseproperties;

    public DAO(){
        databaseproperties= new Databaseproperties();
        System.out.println(databaseproperties.getURL());
        System.out.println(databaseproperties.input);
    }


    public void connect(){
        try{
            conn = DriverManager.getConnection(databaseproperties.getURL(), databaseproperties.getUsername(), databaseproperties.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public Connection getConn(){
        return conn;
    }
}
