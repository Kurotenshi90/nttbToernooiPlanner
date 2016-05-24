package datasource.DAO;

import domain.Vereniging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 24-05-16.
 */
public class VerenigingDao extends DAO {

    public ArrayList<Vereniging> getAllVerenigingen() {
        ArrayList<Vereniging> verenigings = new ArrayList<>();

        connect();
        try {

            ResultSet verenigingResult = conn.prepareStatement("SELECT Verenigingnr, Naam, Straatnaam, Huisnr, Woonplaats FROM Vereniging").executeQuery();

            while(verenigingResult.next()){
                verenigings.add(new Vereniging(verenigingResult.getInt(1), verenigingResult.getString(2), verenigingResult.getString(3), verenigingResult.getInt(4), verenigingResult.getString(5)));
            }


        } catch(Exception e) {
            e.printStackTrace();
        }

        disconnect();
        return verenigings;
    }

    public void saveVereniging(Vereniging vereniging){
        connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_InsertVereniging ? ,? ,?, ?, ?, ?, ?");
            preparedStatement.setString(1, vereniging.getNaam());
            preparedStatement.setString(2, vereniging.getEmailAdres());
            preparedStatement.setString(3, vereniging.getStraatnaam());
            preparedStatement.setInt(4, vereniging.getHuisnummer());
            preparedStatement.setString(5, vereniging.getPostcode());
            preparedStatement.setString(6, vereniging.getWoonplaats());
            preparedStatement.setInt(7, vereniging.getTelefoonnummer());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }
}
