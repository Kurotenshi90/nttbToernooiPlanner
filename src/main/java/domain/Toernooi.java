package domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Peter-Paul on 20/04/2016.
 */
public class Toernooi {
    public int ID;
    public String naam;
    public Date begindatum;
    public Date einddatum;
    public Date inschrijfdatum;
    public Locatie locatie;
    public String betalingsinformatie;
    public String toernooisoort;
    private ArrayList<CommissieLidInToernooi> commisieLidInToernooi;
    private ArrayList<Deeltoernooi> deeltoernoois;

    public Toernooi() {
        commisieLidInToernooi = new ArrayList<>();
    }



    public ArrayList<CommissieLidInToernooi> getCommisieLidInToernooi() {
        return commisieLidInToernooi;
    }

    public void setCommisieLidInToernooi(ArrayList<CommissieLidInToernooi> commisieLidInToernooi) {
        this.commisieLidInToernooi = commisieLidInToernooi;
    }

    public ArrayList<Deeltoernooi> getDeeltoernoois() {
        return deeltoernoois;
    }

    public void setDeeltoernoois(ArrayList<Deeltoernooi> deeltoernoois) {
        this.deeltoernoois = deeltoernoois;
    }

    public String getToernooisoort() {
        return toernooisoort;
    }

    public void setToernooisoort(String toernooisoort) {
        this.toernooisoort = toernooisoort;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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

    public String getBetalingsinformatie() {
        return betalingsinformatie;
    }

    public void setBetalingsinformatie(String betalingsinformatie) {
        this.betalingsinformatie = betalingsinformatie;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
