package datasource.DAO;

import domain.Spelvorm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class SpelvormDao extends DAO {

    public ArrayList<Spelvorm> getSpelvormen() {
        ArrayList<Spelvorm> spelvormen = new ArrayList<>();
        connect();

        try {
            ResultSet resultSet = conn.prepareStatement("SELECT Spelvorm FROM Spelvorm").executeQuery();

            while(resultSet.next()){
                spelvormen.add(new Spelvorm(resultSet.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spelvormen;
    }
}
