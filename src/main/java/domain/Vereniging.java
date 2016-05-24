package domain;

/**
 * Created by donnyolijslager on 24-05-16.
 */
public class Vereniging {
    private int verenigingnr;
    private String naam;
    private String straatnaam;
    private int huisnummer;
    private String woonplaats;
    private String emailAdres;
    private String postcode;
    private int telefoonnummer;


    public Vereniging(String naam, String straatnaam, int huisnummer, String woonplaats, String emailAdres, String postcode, int telefoonnummer) {
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.woonplaats = woonplaats;
        this.emailAdres = emailAdres;
        this.postcode = postcode;
        this.telefoonnummer = telefoonnummer;
    }

    public Vereniging(int verenigingnr, String naam, String straatnaam, int huisnummer, String woonplaats) {
        this.verenigingnr = verenigingnr;
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.woonplaats = woonplaats;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(int telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public int getVerenigingnr() {
        return verenigingnr;
    }

    public void setVerenigingnr(int verenigingnr) {
        this.verenigingnr = verenigingnr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String toString(){
        return "Naam: " + naam + ", Woonplaats: " + woonplaats + ", Straatnaam: " + straatnaam;
    }
}
