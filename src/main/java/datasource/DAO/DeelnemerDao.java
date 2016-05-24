package datasource.DAO;

import domain.CommisieLidInToernooi;
import domain.Deelnemer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeelnemerDao extends DAO {

    public void saveDeelnemers(ArrayList<Deelnemer> deelnemers){
        String updateString = "EXEC STP_SignInForTournament ?, ?";
        connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updateString);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[{");
            boolean checkPastFirstRound= false;
            for(Deelnemer d: deelnemers) {
                if (checkPastFirstRound) {
                    stringBuilder.append("},{");
                }

                stringBuilder.append("\"DeelToernooinr\":" + d.getDeelToernooinr() + ",");
                stringBuilder.append("\"Voornaam\":\"" + d.getVoornaam() + "\",");
                stringBuilder.append("\"Achternaam\":\"" + d.getAchternaam() + "\",");
                stringBuilder.append("\"BondsNr\":" + d.getBondsnr() + ",");
                stringBuilder.append("\"Geslacht\":\"" + d.getGeslacht() + "\",");
                stringBuilder.append("\"Licentie\":\"" + d.getLicentie()+"\",");
                stringBuilder.append("\"Verenigingnr\":" + d.getVerenigingnr());

                checkPastFirstRound = true;

            }
            stringBuilder.append("}]");
            preparedStatement.setString(1, stringBuilder.toString());

            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("[{");
            checkPastFirstRound= false;
            for(Deelnemer d: deelnemers) {
                if(d.getBondsnrPartner() != 0) {
                    if (checkPastFirstRound) {
                        stringBuilder1.append("},{");
                    }

                    stringBuilder1.append("\"BondsNr\":" + d.getBondsnr() + ",");
                    stringBuilder1.append("\"BondsNrPartner\":" + d.getBondsnrPartner() + ",");
                    stringBuilder1.append("\"DeelToernooiNr\":" + d.getDeelToernooinr());

                    checkPastFirstRound = true;
                }
            }
            stringBuilder1.append("}]");
            preparedStatement.setString(2, stringBuilder1.toString());

            preparedStatement.executeUpdate();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
