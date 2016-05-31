package domain;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class Tafel {
    private String naam;
    private int toernooinummer;
    private int tafelnummer;
    private int wedstrijdnummer;

    public Tafel(String naam, int toernooinummer, int tafelnummer, int wedstrijdnummer) {
        this.naam = naam;
        this.toernooinummer = toernooinummer;
        this.tafelnummer = tafelnummer;
        this.wedstrijdnummer = wedstrijdnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getToernooinummer() {
        return toernooinummer;
    }

    public void setToernooinummer(int toernooinummer) {
        this.toernooinummer = toernooinummer;
    }

    public int getTafelnummer() {
        return tafelnummer;
    }

    public void setTafelnummer(int tafelnummer) {
        this.tafelnummer = tafelnummer;
    }

    public int getWedstrijdnummer() {
        return wedstrijdnummer;
    }

    public void setWedstrijdnummer(int wedstrijdnummer) {
        this.wedstrijdnummer = wedstrijdnummer;
    }
}
