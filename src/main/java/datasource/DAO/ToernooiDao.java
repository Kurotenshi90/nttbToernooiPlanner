package datasource.DAO;

import domain.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter-Paul on 11/05/2016.
 */
public class ToernooiDao extends DAO {

    public ArrayList<HomePageToernooi> getHomePageToernoois(){
        ArrayList<HomePageToernooi> values = new ArrayList<>();

        connect();
        ResultSet resultSet = null;

        try {
            resultSet = conn.prepareStatement("SELECT T.Toernooinr, T.Naam, T.InschrijfDatum, T.StartDatum, T.EindDatum, C.Naam, L.Woonplaats, L.Huisnr, L.Straatnaam\n" +
                    "FROM Locatie L RIGHT JOIN Toernooi T ON L.LOCATIENR=T.Locatienr LEFT JOIN ToernooiCommissie TC ON T.Toernooinr=TC.Toernooinr LEFT JOIN Lid C ON TC.lidnr = C.lidnr\n" +
                    "WHERE Rol = 'Leider' AND T.einddatum > GETDATE()").executeQuery();

            while (resultSet.next()){
                HomePageToernooi homePageToernooi = new HomePageToernooi();
                homePageToernooi.setId(resultSet.getInt(1));
                homePageToernooi.setToernooinaam(resultSet.getString(2));
                homePageToernooi.setInschrijdatum(resultSet.getDate(3));
                homePageToernooi.setBegindatum(resultSet.getDate(4));
                homePageToernooi.setEinddatum(resultSet.getDate(5));

                homePageToernooi.setToernooileider(resultSet.getString(6));

                homePageToernooi.setPlaats(resultSet.getString(7));
                homePageToernooi.setNummer(resultSet.getString(8));
                homePageToernooi.setStraat(resultSet.getString(9));
                values.add(homePageToernooi);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();


        return values;
    }

    public Toernooi getOneToernooi(int toernooiID){

        Toernooi toernooi = new Toernooi();
        ArrayList<CommisieLidInToernooi> commissieLeden = new ArrayList<>();
        connect();
        ResultSet toernooiResult = null;
        ResultSet commissieResult = null;

        try {
            toernooiResult = conn.prepareStatement("SELECT T.Toernooinr, T.Naam, T.InschrijfDatum, T.StartDatum, T.EindDatum, L.LOCATIENR, L.Woonplaats, L.Huisnr, L.Straatnaam, T.Betalingsinfo, T.Prijs, T.ToernooiSoort FROM Locatie L RIGHT JOIN Toernooi T ON L.LOCATIENR=T.Locatienr WHERE T.Toernooinr = " + toernooiID).executeQuery();
            commissieResult = conn.prepareStatement("SELECT TC.lidnr, C.Naam, TC.Rol FROM Toernooicommissie TC INNER JOIN lid C ON TC.lidnr=C.lidnr WHERE Toernooinr = " + toernooiID).executeQuery();


            while (toernooiResult.next()){
                toernooi.setID(toernooiResult.getInt(1));
                toernooi.setNaam(toernooiResult.getString(2));
                toernooi.setInschrijfdatum(toernooiResult.getDate(3));
                toernooi.setBegindatum(toernooiResult.getDate(4));
                toernooi.setEinddatum(toernooiResult.getDate(5));
                toernooi.setLocatie(new Locatie(toernooiResult.getInt(6), toernooiResult.getString(7), toernooiResult.getString(9), toernooiResult.getString(8)));
                toernooi.setBetalingsinformatie(toernooiResult.getString(10));
                toernooi.setPrijs(toernooiResult.getDouble(11));
                toernooi.setToernooisoort(toernooiResult.getString(12));
            }

            while (commissieResult.next()){
                CommisieLidInToernooi commissieLid = new CommisieLidInToernooi();
                commissieLid.setLidnr(commissieResult.getInt(1));
                commissieLid.setNaam(commissieResult.getString(2));
                String rol = commissieResult.getString(3);
                if(rol.equals("leider")) {
                    commissieLid.setLeider(true);
                } else if (rol.equals("contactpersoon")){
                    commissieLid.setContactpersoon(true);
                }
                commissieLeden.add(commissieLid);
            }
            toernooi.setCommisieLidInToernooi(commissieLeden);
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return toernooi;
    }

    public void saveToernooi(Toernooi toernooi){
        String update = "EXEC STP_NieuwToernooiToevoegen ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        connect();
        try{

            PreparedStatement preparedStatement = conn.prepareStatement(update);
            preparedStatement.setInt(1, toernooi.getID());
            preparedStatement.setInt(2, toernooi.getLocatie().getLocatienr());
            preparedStatement.setString(3, toernooi.getToernooisoort());
            preparedStatement.setDate(4, new java.sql.Date(toernooi.getBegindatum().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(toernooi.getEinddatum().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(toernooi.getInschrijfdatum().getTime()));
            preparedStatement.setDouble(7, toernooi.getPrijs());
            preparedStatement.setString(8, toernooi.getBetalingsinformatie());
            preparedStatement.setString(9, toernooi.getNaam());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[{");
            boolean checkPastFirstRound= false;
            for(CommisieLidInToernooi c: toernooi.getCommisieLidInToernooi()){
                if(checkPastFirstRound){
                    stringBuilder.append("},{");
                }
                stringBuilder.append("\"Commissielidnr\":"+c.getLidnr()+",");
                stringBuilder.append("\"Toernooinr\":" + 0 + ",");
                String dummy = "";
                if(c.getLeider()){
                    dummy = "leider";
                }
                else if (c.getContactpersoon()){
                    dummy = "contactpersoon";
                }
                stringBuilder.append("\"rol\":\""+dummy+"\"");
                checkPastFirstRound = true;
            }
            stringBuilder.append("}]");
            preparedStatement.setString(10, stringBuilder.toString());
            StringBuilder stringBuilderDeeltoernooi = new StringBuilder();
            stringBuilderDeeltoernooi.append("[{");
            checkPastFirstRound= false;


            for(int i = 0 ; i < toernooi.getDeeltoernoois().size(); i++){
                if(checkPastFirstRound){
                    stringBuilderDeeltoernooi.append("},{");
                }
                stringBuilderDeeltoernooi.append("\"Deeltoernooinr\":"+toernooi.getDeeltoernoois().get(i).getDeeltoernooinr()+",");
                stringBuilderDeeltoernooi.append("\"Referentienr\":"+i+",");
                stringBuilderDeeltoernooi.append("\"SpelType\":\""+toernooi.getDeeltoernoois().get(i).getSpelvorm()+"\",");
                stringBuilderDeeltoernooi.append("\"MaxAantalMensen\":"+toernooi.getDeeltoernoois().get(i).getMaxAantalSpelers()+",");
                String trueOrFalse = "0";
                if(toernooi.getDeeltoernoois().get(i).getGesloten()) {
                    trueOrFalse = "1";
                }
                stringBuilderDeeltoernooi.append("\"Gesloten\":"+trueOrFalse+"");

                checkPastFirstRound = true;
            }
            stringBuilderDeeltoernooi.append("}]");

            preparedStatement.setString(11, stringBuilderDeeltoernooi.toString());

            StringBuilder stringBuilderDeeltoernooiKlasse = new StringBuilder();
            stringBuilderDeeltoernooiKlasse.append("[{");
            checkPastFirstRound= false;
            for(int i = 0 ; i < toernooi.getDeeltoernoois().size(); i++){
                for(int j = 0; j < toernooi.getDeeltoernoois().get(i).getKlasses().size(); j++){
                    if(checkPastFirstRound){
                        stringBuilderDeeltoernooiKlasse.append("},{");
                    }
                    System.out.println(toernooi.getDeeltoernoois().get(i).getKlasses().size());
                    stringBuilderDeeltoernooiKlasse.append("\"Referentienr\":"+i+",");
                    stringBuilderDeeltoernooiKlasse.append("\"LicentieType\":\""+toernooi.getDeeltoernoois().get(i).getKlasses().get(j).getLicentietype()+"\",");
                    stringBuilderDeeltoernooiKlasse.append("\"Klassenaam\":\""+toernooi.getDeeltoernoois().get(i).getKlasses().get(j).getKlassenaam()+"\"");

                    checkPastFirstRound = true;
                }
            }
            stringBuilderDeeltoernooiKlasse.append("}]");
            preparedStatement.setString(12, stringBuilderDeeltoernooiKlasse.toString());

            System.out.println(stringBuilderDeeltoernooiKlasse.toString());
            System.out.println(stringBuilderDeeltoernooi.toString());
            System.out.println(stringBuilder.toString());
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
