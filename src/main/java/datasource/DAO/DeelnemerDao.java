package datasource.DAO;

import domain.CommisieLidInToernooi;
import domain.Deelnemer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class DeelnemerDao extends DAO {

    public ArrayList<Deelnemer> getDeelnemersOfDeeltoernooi(int toernooiID) {
        ArrayList<Deelnemer> deelnemers = new ArrayList<>();

        connect();
        try {
            ResultSet deelnemerResult = conn.prepareStatement("SELECT D.Deelnemernr, D.DeelToernooinr, D.Voornaam, D.Achternaam, D.Bondsnr, D.Geslacht, D.Licentie, D.Verenigingnr, DL.Bondsnr, DT.Startdatum FROM Deelnemer D LEFT JOIN Partner P ON D.Deelnemernr = P.Deelnemernr LEFT JOIN Deelnemer DL ON P.Partnernr=DL.Deelnemernr INNER JOIN Deeltoernooi DT ON D.DeelToernooinr= DT.DeelToernooinr WHERE DT.Toernooinr = " + toernooiID).executeQuery();
            while(deelnemerResult.next()){
                deelnemers.add(new Deelnemer(deelnemerResult.getInt(1),deelnemerResult.getInt(2),deelnemerResult.getString(3),deelnemerResult.getString(4),deelnemerResult.getInt(5), deelnemerResult.getString(6), deelnemerResult.getString(7), deelnemerResult.getInt(9), deelnemerResult.getInt(8), deelnemerResult.getString(10)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return deelnemers;
    }

    public void saveDeelnemers(ArrayList<Deelnemer> deelnemers, int toernooiId){
        String updateString = "EXEC STP_SignInForTournament ?, ?, ?";
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

                stringBuilder.append("\"Deelnemernr\":" + d.getDeelnemerID() + ",");
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
            System.out.println(stringBuilder.toString());
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
            preparedStatement.setInt(3, toernooiId);

            preparedStatement.executeUpdate();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
