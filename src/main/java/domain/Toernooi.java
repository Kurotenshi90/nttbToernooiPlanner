package domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Peter-Paul on 20/04/2016.
 */
public class Toernooi {
    public String naam;
    public Date begindatum;
    public Date einddatum;
    public Date inschrijfdatum;
    public Locatie locatie;
    public double prijs;
    public String betalingsinformatie;
    private List<CommisieLidInToernooi> commisieLidInToernooi;
    private List<ToernooiSysteemType> toernooiSysteemTypes;

    public Toernooi(String naam, String begindatum, String einddatum, String inschrijfdatum, Locatie locatie, double prijs,
                    String betalingsinformatie, List<CommisieLidInToernooi> commisieLidInToernooi, List<ToernooiSysteemType> toernooiSysteemTypes) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.naam = naam;
        this.begindatum = format.parse(begindatum);
        this.einddatum = format.parse(einddatum);
        this.inschrijfdatum = format.parse(inschrijfdatum);
        this.locatie = locatie;
        this.prijs = prijs;
        this.betalingsinformatie = betalingsinformatie;
        this.commisieLidInToernooi = commisieLidInToernooi;
        this.toernooiSysteemTypes = toernooiSysteemTypes;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLeider() {
        String leider= "";
        for(CommisieLidInToernooi item: commisieLidInToernooi) {
            if (item.getLeider()) {
                leider = item.getVoornaam();

            }
        }
        return leider;
    }


    public Date getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(Date begindatum) {
        this.begindatum = begindatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(Date einddatum) {
        this.einddatum = einddatum;
    }

    public Date getInschrijfdatum() {
        return inschrijfdatum;
    }

    public void setInschrijfdatum(Date inschrijfdatum) {
        this.inschrijfdatum = inschrijfdatum;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getBetalingsinformatie() {
        return betalingsinformatie;
    }

    public void setBetalingsinformatie(String betalingsinformatie) {
        this.betalingsinformatie = betalingsinformatie;
    }
}
