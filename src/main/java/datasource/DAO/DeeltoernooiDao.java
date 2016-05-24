package datasource.DAO;

import domain.Deeltoernooi;
import domain.Klasse;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeeltoernooiDao extends DAO {

    public ArrayList<Deeltoernooi> getDeeltoernoois(int toernooiID) {
        ArrayList<Deeltoernooi> deeltoernoois = new ArrayList<>();
        connect();

        ResultSet deeltoernooiResult = null;
        ResultSet deeltoernooiklassesResult = null;

        try {
            deeltoernooiResult = conn.prepareStatement("SELECT  DeelToernooinr, SpelType, MaxAantalMensen, Gesloten, Prijs, Startdatum  FROM DeelToernooi WHERE Toernooinr = " + toernooiID).executeQuery();


            while (deeltoernooiResult.next()) {
                Deeltoernooi deeltoernooi = new Deeltoernooi(deeltoernooiResult.getInt(3), deeltoernooiResult.getInt(1), deeltoernooiResult.getTimestamp(6).toLocalDateTime(), deeltoernooiResult.getDouble(5), deeltoernooiResult.getString(2), deeltoernooiResult.getBoolean(4));
                deeltoernooiklassesResult = conn.prepareStatement("SELECT LicentieType, KlasseNaam FROM KlasseInToernooi WHERE DeelToernooinr = " + deeltoernooi.getDeeltoernooinr()).executeQuery();
                ArrayList<Klasse> klasses = new ArrayList<>();
                while (deeltoernooiklassesResult.next()) {
                    Klasse klasse = new Klasse(deeltoernooiklassesResult.getString(1), deeltoernooiklassesResult.getString(2));
                    klasses.add(klasse);
                }
                deeltoernooi.setKlasses(klasses);
                deeltoernoois.add(deeltoernooi);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return deeltoernoois;
    }
}
