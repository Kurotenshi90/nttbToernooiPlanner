package datasource.DAO;

import domain.HomePageToernooi;

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
}
