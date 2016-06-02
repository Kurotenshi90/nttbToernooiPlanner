package datasource.DAO;

import domain.*;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Peter-Paul on 11/05/2016.
 */
public class ToernooiDao extends DAO {

    public ArrayList<HomePageToernooi> getHomePageToernoois(){
        ArrayList<HomePageToernooi> values = new ArrayList<>();

        connect();
        ResultSet resultSet = null;

        try {
            resultSet = conn.prepareStatement("SELECT T.Toernooinr, T.Naam, T.InschrijfDatum, T.StartDatum, T.EindDatum, C.Naam, L.Woonplaats, L.Huisnr, L.Straatnaam\n" +
                    "FROM Locatie L RIGHT JOIN Toernooi T ON L.LOCATIENR=T.Locatienr LEFT JOIN ToernooiCommissie TC ON T.Toernooinr=TC.Toernooinr LEFT JOIN Lid C ON TC.lidnr = C.lidnr\n" +
                    "WHERE Rol = 'Leider' AND T.einddatum > GETDATE()").executeQuery();

            while (resultSet.next()){
                HomePageToernooi homePageToernooi = new HomePageToernooi();
                homePageToernooi.setId(resultSet.getInt(1));
                homePageToernooi.setToernooinaam(resultSet.getString(2));
                homePageToernooi.setInschrijdatum(resultSet.getDate(3));
                homePageToernooi.setBegindatum(resultSet.getDate(4));
                homePageToernooi.setEinddatum(resultSet.getDate(5));

                homePageToernooi.setToernooileider(resultSet.getString(6));

                homePageToernooi.setPlaats(resultSet.getString(7));
                homePageToernooi.setNummer(resultSet.getString(8));
                homePageToernooi.setStraat(resultSet.getString(9));
                values.add(homePageToernooi);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();


        return values;
    }

    public Toernooi getOneToernooi(int toernooiID){

        Toernooi toernooi = new Toernooi();
        ArrayList<CommisieLidInToernooi> commissieLeden = new ArrayList<>();
        connect();
        ResultSet toernooiResult = null;
        ResultSet commissieResult = null;
        ResultSet deeltoernooiResult = null;
        ResultSet deeltoernooiklassesResult = null;
        ResultSet deeltoernooiDeelnemerResult = null;
        ResultSet deeltoernooiBracketResult = null;

        try {
            toernooiResult = conn.prepareStatement("SELECT T.Toernooinr, T.Naam, T.InschrijfDatum, T.StartDatum, T.EindDatum, L.LOCATIENR, L.Woonplaats, L.Huisnr, L.Straatnaam, T.Betalingsinfo, T.ToernooiSoort FROM Locatie L RIGHT JOIN Toernooi T ON L.LOCATIENR=T.Locatienr WHERE T.Toernooinr = " + toernooiID).executeQuery();
            commissieResult = conn.prepareStatement("SELECT TC.lidnr, C.Naam, TC.Rol FROM Toernooicommissie TC INNER JOIN lid C ON TC.lidnr=C.lidnr WHERE Toernooinr = " + toernooiID).executeQuery();
            deeltoernooiResult = conn.prepareStatement("SELECT  DeelToernooinr, SpelType, MaxAantalMensen, Gesloten, Prijs, Startdatum  FROM DeelToernooi WHERE Toernooinr = "+ toernooiID).executeQuery();

            while (toernooiResult.next()){
                toernooi.setID(toernooiResult.getInt(1));
                toernooi.setNaam(toernooiResult.getString(2));
                toernooi.setInschrijfdatum(toernooiResult.getDate(3));
                toernooi.setBegindatum(toernooiResult.getDate(4));
                toernooi.setEinddatum(toernooiResult.getDate(5));
                toernooi.setLocatie(new Locatie(toernooiResult.getInt(6), toernooiResult.getString(7), toernooiResult.getString(9), toernooiResult.getString(8)));
                toernooi.setBetalingsinformatie(toernooiResult.getString(10));
                toernooi.setToernooisoort(toernooiResult.getString(11));
            }

            while (commissieResult.next()){
                CommisieLidInToernooi commissieLid = new CommisieLidInToernooi();
                commissieLid.setLidnr(commissieResult.getInt(1));
                commissieLid.setNaam(commissieResult.getString(2));
                String rol = commissieResult.getString(3);
                if(rol.equals("leider")) {
                    commissieLid.setLeider(true);
                } else if (rol.equals("contactpersoon")){
                    commissieLid.setContactpersoon(true);
                } else if (rol.equals("bondsVertegenwoordiger")){
                    commissieLid.setBondsVertegenwoordiger(true);
                }
                commissieLeden.add(commissieLid);
            }
            toernooi.setCommisieLidInToernooi(commissieLeden);

            ArrayList<Deeltoernooi> deeltoernoois = new ArrayList<>();
            while (deeltoernooiResult.next()){
                Deeltoernooi deeltoernooi;
                if(toernooi.getToernooisoort().equals("Poule")) {
                    deeltoernooi = new PouleDeeltoernooi(deeltoernooiResult.getInt(3), deeltoernooiResult.getInt(1), deeltoernooiResult.getTimestamp(6).toLocalDateTime(), deeltoernooiResult.getDouble(5), deeltoernooiResult.getString(2), deeltoernooiResult.getBoolean(4));
                    deeltoernooiDeelnemerResult = conn.prepareStatement("SELECT d.Deelnemernr, d.Deeltoernooinr, d.Voornaam, d.Achternaam, d.Bondsnr, d.Geslacht, d.Licentie, d.Verenigingnr, s.poulenr, dl.bondsnr FROM Deelnemer d LEFT JOIN SpelersInPoule s ON d.Deelnemernr=s.deelnemernr LEFT JOIN Partner p ON d.deelnemernr = p.deelnemernr LEFT JOIN Deelnemer DL ON P.Partnernr=DL.Deelnemernr WHERE d.Deeltoernooinr =" + deeltoernooi.getDeeltoernooinr() + "Order by s.poulenr asc").executeQuery();
                    ArrayList<Deelnemer> deelnemers = new ArrayList<>();
                    ArrayList<Poule> poules = new ArrayList<>();
                    while(deeltoernooiDeelnemerResult.next()){
                        if(deeltoernooiDeelnemerResult.getInt(9) != 0){
                                if(poules.size() != deeltoernooiDeelnemerResult.getInt(9)){
                                    poules.add(new Poule("Poule " +deeltoernooiDeelnemerResult.getInt(9)));
                                }
                                poules.get(deeltoernooiDeelnemerResult.getInt(9)-1).addDeelnemer(new Deelnemer(deeltoernooiDeelnemerResult.getInt(1), deeltoernooiDeelnemerResult.getInt(2), deeltoernooiDeelnemerResult.getString(3),
                                        deeltoernooiDeelnemerResult.getString(4), deeltoernooiDeelnemerResult.getInt(5), deeltoernooiDeelnemerResult.getString(6),
                                        deeltoernooiDeelnemerResult.getString(7), deeltoernooiDeelnemerResult.getInt(8), deeltoernooiDeelnemerResult.getInt(10)));

                        } else {

                            deelnemers.add(new Deelnemer(deeltoernooiDeelnemerResult.getInt(1), deeltoernooiDeelnemerResult.getInt(2), deeltoernooiDeelnemerResult.getString(3),
                                    deeltoernooiDeelnemerResult.getString(4), deeltoernooiDeelnemerResult.getInt(5), deeltoernooiDeelnemerResult.getString(6),
                                    deeltoernooiDeelnemerResult.getString(7), deeltoernooiDeelnemerResult.getInt(8), deeltoernooiDeelnemerResult.getInt(10)));
                        }
                    }
                    ((PouleDeeltoernooi)deeltoernooi).setPoules(poules);
                    deeltoernooi.setDeelnemers(deelnemers);
                } else if(toernooi.getToernooisoort().equals("Knockout")) {

                    deeltoernooi = new KnockoutDeeltoernooi(deeltoernooiResult.getInt(3), deeltoernooiResult.getInt(1), deeltoernooiResult.getTimestamp(6).toLocalDateTime(), deeltoernooiResult.getDouble(5), deeltoernooiResult.getString(2), deeltoernooiResult.getBoolean(4));
                    deeltoernooiDeelnemerResult = conn.prepareStatement("SELECT d.Deelnemernr, d.Deeltoernooinr, d.Voornaam, d.Achternaam, d.Bondsnr, d.Geslacht, d.Licentie, d.Verenigingnr, dl.bondsnr, diw.wedstrijdnr, diw.team" +
                            " FROM Deelnemer d left join DeelnemerInWedstrijd diw ON d.Deelnemernr=diw.Deelnemernr" +
                            " left JOIN Wedstrijd w ON diw.Wedstrijdnr=w.wedstrijdnr LEFT JOIN Partner p " +
                            "ON d.deelnemernr = p.deelnemernr left JOIN Deelnemer DL ON P.Partnernr=DL.Deelnemernr" +
                            " WHERE d.Deeltoernooinr =" + deeltoernooi.getDeeltoernooinr()).executeQuery();
                    deeltoernooiBracketResult = conn.prepareStatement("select b.Bracketnr, b.wedstrijdnr" +
                            " from Bracket b inner join Wedstrijd w on b.wedstrijdnr=w.wedstrijdnr" +
                            " where b.Bracketnr > ( select (max(bracketnr))/2 from Bracket WHERE deeltoernooinr = " + deeltoernooi.getDeeltoernooinr() + " ) and w.deeltoernooinr ="+ deeltoernooi.getDeeltoernooinr()).executeQuery();
                    ArrayList<Deelnemer> deelnemers = new ArrayList<>();
                    ArrayList<Bracket> brackets = new ArrayList<>();

                    while(deeltoernooiBracketResult.next()){
                        if(brackets.size() != deeltoernooiBracketResult.getInt(1)){
                            brackets.add(new Bracket(deeltoernooiBracketResult.getInt(1), deeltoernooiBracketResult.getInt(2)));
                        }
                    }
                    while(deeltoernooiDeelnemerResult.next()){
                        if(deeltoernooiDeelnemerResult.getInt(10) != 0){
                            for (Bracket bracket:brackets
                                 ) {
                                if(bracket.getWedstrijdnr() == deeltoernooiDeelnemerResult.getInt(10)){
                                    if(deeltoernooiDeelnemerResult.getInt(11)== 0){
                                        bracket.addSpeler1(new Deelnemer(deeltoernooiDeelnemerResult.getInt(1), deeltoernooiDeelnemerResult.getInt(2), deeltoernooiDeelnemerResult.getString(3),
                                                deeltoernooiDeelnemerResult.getString(4), deeltoernooiDeelnemerResult.getInt(5), deeltoernooiDeelnemerResult.getString(6),
                                                deeltoernooiDeelnemerResult.getString(7), deeltoernooiDeelnemerResult.getInt(8), deeltoernooiDeelnemerResult.getInt(9)));
                                    } else {
                                        bracket.addSpeler2(new Deelnemer(deeltoernooiDeelnemerResult.getInt(1), deeltoernooiDeelnemerResult.getInt(2), deeltoernooiDeelnemerResult.getString(3),
                                                deeltoernooiDeelnemerResult.getString(4), deeltoernooiDeelnemerResult.getInt(5), deeltoernooiDeelnemerResult.getString(6),
                                                deeltoernooiDeelnemerResult.getString(7), deeltoernooiDeelnemerResult.getInt(8), deeltoernooiDeelnemerResult.getInt(9)));
                                    }
                                }

                            }
                        } else {
                            deelnemers.add(new Deelnemer(deeltoernooiDeelnemerResult.getInt(1), deeltoernooiDeelnemerResult.getInt(2), deeltoernooiDeelnemerResult.getString(3),
                                    deeltoernooiDeelnemerResult.getString(4), deeltoernooiDeelnemerResult.getInt(5), deeltoernooiDeelnemerResult.getString(6),
                                    deeltoernooiDeelnemerResult.getString(7), deeltoernooiDeelnemerResult.getInt(8), deeltoernooiDeelnemerResult.getInt(9)));
                        }

                    }
                    ((KnockoutDeeltoernooi)deeltoernooi).setBrackets(brackets);
                    deeltoernooi.setDeelnemers(deelnemers);


                } else {
                    deeltoernooi = new Deeltoernooi(deeltoernooiResult.getInt(3), deeltoernooiResult.getInt(1), deeltoernooiResult.getTimestamp(6).toLocalDateTime(), deeltoernooiResult.getDouble(5), deeltoernooiResult.getString(2), deeltoernooiResult.getBoolean(4));
                }
                deeltoernooiklassesResult = conn.prepareStatement("SELECT LicentieType, KlasseNaam FROM KlasseInToernooi WHERE DeelToernooinr = "+ deeltoernooi.getDeeltoernooinr()).executeQuery();
                ArrayList<Klasse> klasses = new ArrayList<>();
                while(deeltoernooiklassesResult.next()) {
                    Klasse klasse = new Klasse(deeltoernooiklassesResult.getString(1), deeltoernooiklassesResult.getString(2));
                    klasses.add(klasse);
                }
                deeltoernooi.setKlasses(klasses);
                deeltoernoois.add(deeltoernooi);
            }
            toernooi.setDeeltoernoois(deeltoernoois);
            disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

        return toernooi;
    }

    public void saveToernooi(Toernooi toernooi){
        String update = "EXEC STP_NieuwToernooiToevoegen ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        connect();
        try{

            PreparedStatement preparedStatement = conn.prepareStatement(update);
            preparedStatement.setInt(1, toernooi.getID());
            preparedStatement.setInt(2, toernooi.getLocatie().getLocatienr());
            preparedStatement.setString(3, toernooi.getToernooisoort());
            preparedStatement.setDate(4, new java.sql.Date(toernooi.getBegindatum().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(toernooi.getEinddatum().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(toernooi.getInschrijfdatum().getTime()));
            preparedStatement.setString(7, toernooi.getBetalingsinformatie());
            preparedStatement.setString(8, toernooi.getNaam());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[{");
            boolean checkPastFirstRound= false;
            for(CommisieLidInToernooi c: toernooi.getCommisieLidInToernooi()){
                if(checkPastFirstRound){
                    stringBuilder.append("},{");
                }
                stringBuilder.append("\"Commissielidnr\":"+c.getLidnr()+",");
                stringBuilder.append("\"Toernooinr\":" + 0 + ",");
                String dummy = "";
                if(c.getLeider()){
                    dummy = "leider";
                }
                else if (c.getContactpersoon()){
                    dummy = "contactpersoon";
                } else if (c.getBondsVertegenwoordiger()){
                    dummy = "bondsVertegenwoordiger";
                }
                stringBuilder.append("\"rol\":\""+dummy+"\"");
                checkPastFirstRound = true;
            }
            stringBuilder.append("}]");
            preparedStatement.setString(9, stringBuilder.toString());
            StringBuilder stringBuilderDeeltoernooi = new StringBuilder();
            stringBuilderDeeltoernooi.append("[{");
            checkPastFirstRound= false;

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for(int i = 0 ; i < toernooi.getDeeltoernoois().size(); i++){
                if(checkPastFirstRound){
                    stringBuilderDeeltoernooi.append("},{");
                }
                stringBuilderDeeltoernooi.append("\"Deeltoernooinr\":"+toernooi.getDeeltoernoois().get(i).getDeeltoernooinr()+",");
                stringBuilderDeeltoernooi.append("\"Referentienr\":"+i+",");
                stringBuilderDeeltoernooi.append("\"SpelType\":\""+toernooi.getDeeltoernoois().get(i).getSpelvorm()+"\",");
                stringBuilderDeeltoernooi.append("\"MaxAantalMensen\":"+toernooi.getDeeltoernoois().get(i).getMaxAantalSpelers()+",");
                String trueOrFalse = "0";
                if(toernooi.getDeeltoernoois().get(i).getGesloten()) {
                    trueOrFalse = "1";
                }
                stringBuilderDeeltoernooi.append("\"Gesloten\":"+trueOrFalse+",");
                stringBuilderDeeltoernooi.append("\"Prijs\":\""+toernooi.getDeeltoernoois().get(i).getPrijs()+"\",");
                stringBuilderDeeltoernooi.append("\"StartDatum\":\""+toernooi.getDeeltoernoois().get(i).getBeginTijd().format(dateTimeFormatter)+"\"");
                System.out.println(toernooi.getDeeltoernoois().get(i).getBeginTijd().format(dateTimeFormatter));

                checkPastFirstRound = true;
            }
            stringBuilderDeeltoernooi.append("}]");

            preparedStatement.setString(10, stringBuilderDeeltoernooi.toString());

            StringBuilder stringBuilderDeeltoernooiKlasse = new StringBuilder();
            stringBuilderDeeltoernooiKlasse.append("[{");
            checkPastFirstRound= false;
            for(int i = 0 ; i < toernooi.getDeeltoernoois().size(); i++){
                for(int j = 0; j < toernooi.getDeeltoernoois().get(i).getKlasses().size(); j++){
                    if(checkPastFirstRound){
                        stringBuilderDeeltoernooiKlasse.append("},{");
                    }
                    System.out.println(toernooi.getDeeltoernoois().get(i).getKlasses().size());
                    stringBuilderDeeltoernooiKlasse.append("\"Referentienr\":"+i+",");
                    stringBuilderDeeltoernooiKlasse.append("\"LicentieType\":\""+toernooi.getDeeltoernoois().get(i).getKlasses().get(j).getLicentietype()+"\",");
                    stringBuilderDeeltoernooiKlasse.append("\"Klassenaam\":\""+toernooi.getDeeltoernoois().get(i).getKlasses().get(j).getKlassenaam()+"\"");

                    checkPastFirstRound = true;
                }
            }
            stringBuilderDeeltoernooiKlasse.append("}]");
            preparedStatement.setString(11, stringBuilderDeeltoernooiKlasse.toString());

            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveToernooiIndeling(Deeltoernooi deeltoernooi) {
        try {
            if(deeltoernooi instanceof PouleDeeltoernooi) {
                connect();


                PouleDeeltoernooi genereerPoules = (PouleDeeltoernooi) deeltoernooi;
                PreparedStatement generatePoules = conn.prepareStatement("EXEC STP_PoulesAanmaken ?, ?");
                generatePoules.setInt(1, genereerPoules.getDeeltoernooinr());
                generatePoules.setInt(2, genereerPoules.getPoules().size());
                generatePoules.executeUpdate();
                disconnect();
                connect();

                PreparedStatement schrijfSpelersIn = conn.prepareStatement("EXEC STP_InsertDeelnemerIntoPoule ?");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[{");
                boolean checkPastFirstRound= false;
                for(int i = 0; i<((PouleDeeltoernooi) deeltoernooi).getPoules().size();i++){
                    for(Deelnemer deelnemer: ((PouleDeeltoernooi) deeltoernooi).getPoules().get(i).getDeelnemers()){
                        if(checkPastFirstRound){
                            stringBuilder.append("},{");
                        }
                        stringBuilder.append("\"Deelnemernr\":"+deelnemer.getDeelnemerID()+",");
                        stringBuilder.append("\"Deeltoernooinr\":"+deeltoernooi.getDeeltoernooinr()+",");
                        stringBuilder.append("\"Poulenr\":"+(i+1));
                        checkPastFirstRound = true;
                    }
                }
                stringBuilder.append("}]");
                schrijfSpelersIn.setString(1, stringBuilder.toString());
                schrijfSpelersIn.executeUpdate();

                disconnect();
            } else if(deeltoernooi instanceof KnockoutDeeltoernooi){
                connect();

                KnockoutDeeltoernooi planSpelers = (KnockoutDeeltoernooi) deeltoernooi;
                PreparedStatement planSpelersIn = conn.prepareStatement("EXEC STP_InsertDeelnemerIntoWedstrijdKnockout ?, ?");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[{");
                boolean checkPastFirstRound= false;
                for(int i = 0; i<planSpelers.getBrackets().size();i++){
                    maakDeelnemerJson(stringBuilder, planSpelers, planSpelers.getBrackets().get(i).getSpeler1(), i, checkPastFirstRound, false);
                    stringBuilder.append("},{");
                    maakDeelnemerJson(stringBuilder, planSpelers, planSpelers.getBrackets().get(i).getSpeler2(), i, checkPastFirstRound, true);
                }
                stringBuilder.append("}]");
                System.out.println(stringBuilder.toString());
                planSpelersIn.setString(1, stringBuilder.toString());
                planSpelersIn.setInt(2, planSpelers.getDeeltoernooinr());
                planSpelersIn.executeUpdate();
                disconnect();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void maakDeelnemerJson(StringBuilder stringBuilder, KnockoutDeeltoernooi planSpelers, ArrayList<Deelnemer> deelnemers, int i, boolean checkPastFirstRound, boolean speler2){
        for(Deelnemer deelnemer: deelnemers){
            if(checkPastFirstRound){
                stringBuilder.append("},{");
            }
            stringBuilder.append("\"Deelnemernr\":"+deelnemer.getDeelnemerID()+",");
            stringBuilder.append("\"Wedstrijdnr\":"+planSpelers.getBrackets().get(i).getWedstrijdnr()+",");
            if(speler2) {
                stringBuilder.append("\"Team\":" + 1);
            } else {
                stringBuilder.append("\"Team\":" + 0);
            }

            checkPastFirstRound = true;
        }

    }

    public void planEnSluitDeeltoernooiPlanning(Deeltoernooi deeltoernooi, int teWinnenWedstrijden){
        try {
            connect();
            PreparedStatement maakWedstrijden = conn.prepareStatement("EXEC STP_PouleWedstrijdenAanmaken ?, ?");
            maakWedstrijden.setInt(1, deeltoernooi.getDeeltoernooinr());
            maakWedstrijden.setInt(2, teWinnenWedstrijden);
            maakWedstrijden.executeUpdate();
            disconnect();
            connect();
            PreparedStatement deelWedstrijdenIn = conn.prepareStatement("EXEC STP_PlanWedstrijdenPoule ?");
            deelWedstrijdenIn.setInt(1, deeltoernooi.getDeeltoernooinr());
            deelWedstrijdenIn.executeUpdate();
            disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void knockoutToernooiAanmaken(Deeltoernooi deeltoernooi){

        try {
            connect();
            ResultSet resultSet = conn.prepareStatement("SELECT 1 FROM Bracket WHERE Deeltoernooinr = "+ deeltoernooi.getDeeltoernooinr()).executeQuery();
            while(!resultSet.isBeforeFirst()){
                PreparedStatement preparedStatement = conn.prepareStatement("EXEC STP_KnockoutWedstrijdenAanmaken ?, ?");
                preparedStatement.setInt(1, deeltoernooi.getDeeltoernooinr());
                preparedStatement.setInt(2, 3);
                preparedStatement.executeUpdate();
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteToernooi(int toernooiID) {
        String delete = "EXEC Stp_DeleteToernooi ?";
        connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setInt(1, toernooiID);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
