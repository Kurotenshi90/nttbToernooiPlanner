package datasource.DAO;

import domain.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenDao extends DAO {
    private ArrayList<Tafel> getNietBezetteTafels(int toernooiId){
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

    public ArrayList<Ronde> getRondesOpToernooi(int toernooiId){
        connect();
        ArrayList<Ronde> rondes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select w.Wedstrijdnr, r.Rondenr, r.ScoreA, r.ScoreB from ronde r inner join Wedstrijd w on w.Wedstrijdnr = r.Wedstrijdnr inner join DeelToernooi d on d.DeelToernooinr = w.DeelToernooinr where d.Toernooinr = ?");
            preparedStatement.setInt(1, toernooiId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Ronde ronde = new Ronde(resultSet.getInt(3), resultSet.getInt(4));
                ronde.setRondenr(resultSet.getInt(2));
                ronde.setWedstrijdnr(resultSet.getInt(1));
                rondes.add(ronde);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rondes;
    }

    public PlanningWedstrijd getwedstrijden(int toernooiId){
        PlanningWedstrijd planningWedstrijd = null;
        ArrayList<Wedstrijd> wedstrijd = new ArrayList<>();
        ArrayList<Tafel> tafels = getNietBezetteTafels(toernooiId);
        try{
            connect();
            PreparedStatement preparedStatement = conn.prepareStatement("select d.DeelToernooinr, w.TeWinnenRondes, w.Wedstrijdnr, w.DeelToernooinr, d.Startdatum, t.TAFELNR, di.Team, dn.Voornaam, dn.Achternaam, dn.Bondsnr from DeelToernooi d \n" +
                                                                        "inner join \n" +
                                                                        "Wedstrijd w on  d.DeelToernooinr = w.DeelToernooinr \n" +
                                                                        "inner join \n" +
                                                                        "DeelnemerInWedstrijd di on di.Wedstrijdnr = w.Wedstrijdnr\n" +
                                                                        "inner join \n" +
                                                                        "Deelnemer dn on dn.Deelnemernr = di.Deelnemernr\n" +
                                                                        "left join Tafels t on t.Wedstrijdnr = w.Wedstrijdnr  \n" +
                                                                        "where d.Toernooinr = ? and w.TeWinnenRondes >= 1 ");
            preparedStatement.setInt(1, toernooiId);
            ResultSet rs = preparedStatement.executeQuery();
            PreparedStatement preparedStatement2 = conn.prepareStatement("select d.DeelToernooinr, w.TeWinnenRondes, w.Wedstrijdnr, d.Startdatum, t.TAFELNR from DeelToernooi d \n" +
                                                                         "left join \n" +
                                                                         "Wedstrijd w on  d.DeelToernooinr = w.DeelToernooinr \n" +
                                                                         "left join Tafels t on t.Wedstrijdnr = w.Wedstrijdnr  \n" +
                                                                         "where d.Toernooinr = ? and w.TeWinnenRondes >= 1 and exists (select 1 from DeelnemerInWedstrijd di where di.Wedstrijdnr = w.Wedstrijdnr)");

            preparedStatement2.setInt(1, toernooiId);
            ResultSet rs2 = preparedStatement2.executeQuery();

            while(rs2.next()){
                Wedstrijd wedstrijd1 = new Wedstrijd(rs2.getInt(1), rs2.getInt(2),rs2.getInt(3),rs2.getTimestamp(4).toLocalDateTime());
                if(rs2.getInt(5)!= 0){
                    for(Tafel t: tafels){
                        if(t.getTafelnummer() == rs2.getInt(5)){
                            wedstrijd1.setTafel(t);
                        }
                    }
                }
                wedstrijd.add(wedstrijd1);

            }

            while(rs.next()){
                for(Wedstrijd w : wedstrijd){
                    if(rs.getInt(3) == w.getWedstrijdnr()){
                        if(rs.getBoolean(7)){
                            w.addSpeler2(new SpelerInWedstrijd(rs.getString(8),rs.getString(9), rs.getInt(10)));
                        }else{
                            w.addSpeler1(new SpelerInWedstrijd(rs.getString(8),rs.getString(9), rs.getInt(10)));
                        }
                    }
                }
            }

            for(Wedstrijd w: wedstrijd){
                tafels.remove(w.getTafel());
            }

            disconnect();
            planningWedstrijd = new PlanningWedstrijd(wedstrijd, tafels);

        }catch (Exception e){
            e.printStackTrace();
        }
        return planningWedstrijd;
    }

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

    public void koppelTafel(Wedstrijd wedstrijd, Tafel tafel){
        connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_KoppelWedstrijdAanTafel ?, ?");
            preparedStatement.setInt(1, wedstrijd.getWedstrijdnr());
            preparedStatement.setInt(2, tafel.getTafelnummer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void onKoppelTafel(Wedstrijd wedstrijd){
        connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_OnKoppelWedstrijdAanTafel ?");
            preparedStatement.setInt(1, wedstrijd.getTafel().getTafelnummer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void voerRondeIn(int wedstrijdnr, Ronde ronde){
        connect();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("EXEC STP_RondeInvoeren ?,?, ?");
            preparedStatement.setInt(1, wedstrijdnr);
            preparedStatement.setInt(2, ronde.getScoreA());
            preparedStatement.setInt(3, ronde.getScoreB());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    public void verwijderRonde(Ronde ronde){
        connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_RondeVerwijderen ?, ?");
            preparedStatement.setInt(1, ronde.getWedstrijdnr());
            preparedStatement.setInt(2, ronde.getRondenr());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public ArrayList<Wedstrijd> getWedstrijdenOpDeeltoernooi(int deeltoernooinr){
        ArrayList<Wedstrijd> wedstrijd = new ArrayList<>();
        connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select d.DeelToernooinr, w.TeWinnenRondes, w.Wedstrijdnr, w.DeelToernooinr, d.Startdatum, t.TAFELNR, di.Team, dn.Voornaam, dn.Achternaam, dn.Bondsnr from DeelToernooi d \n" +
                    "inner join \n" +
                    "Wedstrijd w on  d.DeelToernooinr = w.DeelToernooinr \n" +
                    "inner join \n" +
                    "DeelnemerInWedstrijd di on di.Wedstrijdnr = w.Wedstrijdnr\n" +
                    "inner join \n" +
                    "Deelnemer dn on dn.Deelnemernr = di.Deelnemernr\n" +
                    "left join Tafels t on t.Wedstrijdnr = w.Wedstrijdnr  \n" +
                    "where w.Deeltoernooinr = ? and w.TeWinnenRondes >= 1 " +
                    "and w.TeWinnenRondes > (select count(*) from Ronde r where r.Wedstrijdnr = w.Wedstrijdnr)");
            preparedStatement.setInt(1, deeltoernooinr);
            ResultSet rs = preparedStatement.executeQuery();
            PreparedStatement preparedStatement2 = conn.prepareStatement("select d.DeelToernooinr, w.TeWinnenRondes, w.Wedstrijdnr, d.Startdatum, t.TAFELNR from DeelToernooi d \n" +
                    "left join \n" +
                    "Wedstrijd w on  d.DeelToernooinr = w.DeelToernooinr \n" +
                    "left join Tafels t on t.Wedstrijdnr = w.Wedstrijdnr  \n" +
                    "where w.deeltoernooinr = ? and w.TeWinnenRondes >= 1 and exists (select 1 from DeelnemerInWedstrijd di where di.Wedstrijdnr = w.Wedstrijdnr) and w.TeWinnenRondes > (select count(*) from Ronde r where r.Wedstrijdnr = w.Wedstrijdnr)");

            preparedStatement2.setInt(1, deeltoernooinr);
            ResultSet rs2 = preparedStatement2.executeQuery();

            while(rs2.next()){
                Wedstrijd wedstrijd1 = new Wedstrijd(rs2.getInt(1), rs2.getInt(2),rs2.getInt(3),rs2.getTimestamp(4).toLocalDateTime());
                wedstrijd.add(wedstrijd1);

            }

            while(rs.next()){
                for(Wedstrijd w : wedstrijd){
                    if(rs.getInt(3) == w.getWedstrijdnr()){
                        if(rs.getBoolean(7)){
                            w.addSpeler2(new SpelerInWedstrijd(rs.getString(8),rs.getString(9), rs.getInt(10)));
                        }else{
                            w.addSpeler1(new SpelerInWedstrijd(rs.getString(8),rs.getString(9), rs.getInt(10)));
                        }
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return wedstrijd;
    }


}
