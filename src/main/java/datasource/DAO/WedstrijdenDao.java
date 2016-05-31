package datasource.DAO;

import domain.Tafel;
import domain.Wedstrijd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenDao extends DAO {
    public ArrayList<Tafel> getNietBezetteTafels(int toernooiId){
        ArrayList<Tafel> tafels = new ArrayList<>();
        try{
            connect();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Row_Number() Over(ORDER BY TAFELNR),toernooinr, TAFELNR, Wedstrijdnr FROM tafels WHERE toernooinr = ?");
            preparedStatement.setInt(1, toernooiId);
            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()){
                tafels.add(new Tafel("Tafel "+rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }

            disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }
        return tafels;
    }

//    public ArrayList<Wedstrijd> getwedstrijden(int toernooiId){
//        ArrayList<Tafel> tafels = new ArrayList<>();
//        try{
//            connect();
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Row_Number() Over(ORDER BY TAFELNR),toernooinr, TAFELNR, Wedstrijdnr FROM tafels WHERE toernooinr = ?");
//            preparedStatement.setInt(1, toernooiId);
//            ResultSet rs = preparedStatement.executeQuery();
//
//
//            while(rs.next()){
//                tafels.add(new Tafel("Tafel "+rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
//            }
//
//            disconnect();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return tafels;
//    }

    public void addTafels(int toernooinummer, int aantal){
        try {
            connect();
            PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_maakTafelsAan ?, ?");
            preparedStatement.setInt(1, toernooinummer);
            preparedStatement.setInt(2, aantal);
            preparedStatement.executeUpdate();

            disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
