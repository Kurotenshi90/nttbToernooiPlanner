package datasource.DAO;

import domain.PlanningWedstrijd;
import domain.SpelerInWedstrijd;
import domain.Tafel;
import domain.Wedstrijd;

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
                                                                        "where d.Toernooinr = ? and w.TeWinnenRondes >= 1");
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

    }



}
