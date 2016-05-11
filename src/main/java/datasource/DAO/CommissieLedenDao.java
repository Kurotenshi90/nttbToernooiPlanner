package datasource.DAO;

import domain.NieuwToernooiCommissieLeden;
import presentation.controllers.NiewToernooiController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 11-05-16.
 */
public class CommissieLedenDao extends DAO {

    public ArrayList<NieuwToernooiCommissieLeden> getNieuwToernooiCommissieLeden() {
        ArrayList<NieuwToernooiCommissieLeden> returnValue = new ArrayList<>();

        connect();

        try {
        ResultSet resultSet = conn.prepareStatement("SELECT naam FROM CommissieLid").executeQuery();
            while (resultSet.next()){
                NieuwToernooiCommissieLeden nieuwToernooiCommissieLeden = new NieuwToernooiCommissieLeden();

                nieuwToernooiCommissieLeden.setNaam(resultSet.getString(1));
                returnValue.add(nieuwToernooiCommissieLeden);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }


        return returnValue;
    }

}
