package domain;

import java.util.Date;

/**
 * Created by Peter-Paul on 11/05/2016.
 */
public class HomePageToernooi {
    private int id;
    private String toernooinaam;
    private String toernooileider;
    private Date inschrijdatum;
    private Date begindatum;
    private Date einddatum;
    private String plaats;
    private String straat;
    private String nummer;

//    public HomePageToernooi(String toernooinaam, String toernooileider, Date inschrijdatum, Date begindatum, Date einddatum, String plaats, String straat, String nummer) {
//
//        this.toernooinaam = toernooinaam;
//        this.toernooileider = toernooileider;
//        this.inschrijdatum = inschrijdatum;
//        this.begindatum = begindatum;
//        this.einddatum = einddatum;
//        this.plaats = plaats;
//        this.straat = straat;
//        this.nummer = nummer;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToernooinaam() {
        return toernooinaam;
    }

    public void setToernooinaam(String toernooinaam) {
        this.toernooinaam = toernooinaam;
    }

    public String getToernooileider() {
        return toernooileider;
    }

    public void setToernooileider(String toernooileider) {
        this.toernooileider = toernooileider;
    }

    public Date getInschrijdatum() {
        return inschrijdatum;
    }

    public void setInschrijdatum(Date inschrijdatum) {
        this.inschrijdatum = inschrijdatum;
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

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }
}
