package datasource.DAO;

import domain.Commissielid;
import domain.NieuwToernooiCommissieLeden;

import java.sql.PreparedStatement;
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
        ResultSet resultSet = conn.prepareStatement("SELECT naam, lidnr FROM Lid").executeQuery();
            while (resultSet.next()){
                NieuwToernooiCommissieLeden nieuwToernooiCommissieLeden = new NieuwToernooiCommissieLeden();

                nieuwToernooiCommissieLeden.setNaam(resultSet.getString(1));
                nieuwToernooiCommissieLeden.setLidnr(resultSet.getInt(2));
                returnValue.add(nieuwToernooiCommissieLeden);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }


        return returnValue;
    }

    public void saveCommissielid(Commissielid commissielid){
        String update = "EXEC Stp_VoegCommissielidToe ?, ?, ?, ?, ?";
        connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(update);
            preparedStatement.setString(1, commissielid.getNaam());
            preparedStatement.setString(2, commissielid.getStraatnaam());
            preparedStatement.setInt(3, commissielid.getHuisnr());
            preparedStatement.setString(4, commissielid.getPostcode());
            preparedStatement.setString(5, commissielid.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
