package datasource.DAO;

import domain.Klasse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class KlasseDao extends DAO {

    public ArrayList<Klasse> getKlasses() {
        ArrayList<Klasse> klasses = new ArrayList<>();
        connect();

        ResultSet resultSet = null;
        try {
            resultSet = conn.prepareStatement("SELECT LicentieType, KlasseNaam FROM Klasse").executeQuery();

            while(resultSet.next()){
                klasses.add(new Klasse(resultSet.getString(1), resultSet.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return klasses;
    }
}
