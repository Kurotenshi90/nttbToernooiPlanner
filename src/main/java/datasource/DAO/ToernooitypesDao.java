package datasource.DAO;

import domain.Toernooitype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class ToernooitypesDao extends DAO {

    public ArrayList<Toernooitype> getToernooitypesDao() {
        ArrayList<Toernooitype> toernooitypes = new ArrayList<>();
        connect();

        try {
            ResultSet resultSet = conn.prepareStatement("SELECT ToernooiSoort FROM ToernooiSoort").executeQuery();

            while(resultSet.next()){
                toernooitypes.add(new Toernooitype(resultSet.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();

        return toernooitypes;

    }
}
