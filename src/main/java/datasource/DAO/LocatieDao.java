package datasource.DAO;

import domain.Locatie;
import domain.NieuwToernooiCommissieLeden;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 12-05-16.
 */
public class LocatieDao extends DAO {

    public ArrayList<Locatie> getLocaties() {
        ArrayList<Locatie> returnValue = new ArrayList<>();

        connect();

        try {
            ResultSet resultSet = conn.prepareStatement("SELECT LOCATIENR, Woonplaats, Straatnaam, Huisnr FROM Locatie").executeQuery();
            while (resultSet.next()){
                Locatie locatie = new Locatie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                returnValue.add(locatie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return returnValue;
    }
}
