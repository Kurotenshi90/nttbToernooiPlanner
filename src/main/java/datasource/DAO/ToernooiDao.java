package datasource.DAO;

import domain.CommisieLidInToernooi;
import domain.HomePageToernooi;
import domain.Toernooi;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    "FROM Locatie L RIGHT JOIN Toernooi T ON L.LOCATIENR=T.Locatienr LEFT JOIN ToernooiCommissie TC ON T.Toernooinr=TC.Toernooinr LEFT JOIN CommissieLid C ON TC.Commissielidnr = C.Commissielidnr\n" +
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

    public void saveToernooi(Toernooi toernooi){
        String update = "EXEC STP_NieuwToernooiToevoegen ?, ?, ?, ?, ?, ?, ?, ?, ?";
        connect();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(update);
            preparedStatement.setInt(1, toernooi.getLocatie().getLocatienr());
            preparedStatement.setString(2, toernooi.getToernooisoort());
            preparedStatement.setDate(3, new java.sql.Date(toernooi.getBegindatum().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(toernooi.getEinddatum().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(toernooi.getInschrijfdatum().getTime()));
            preparedStatement.setDouble(6, toernooi.getPrijs());
            preparedStatement.setString(7, toernooi.getBetalingsinformatie());
            preparedStatement.setString(8, toernooi.getNaam());
           // SQLServerDataTable
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
            System.out.println(stringBuilder.toString());

            preparedStatement.setString(9, stringBuilder.toString());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
