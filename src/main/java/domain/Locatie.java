package domain;

/**
 * Created by Peter-Paul on 20/04/2016.
 */
public class Locatie {
    public int locatienr;
    public String plaats;
    public String straatnaam;
    public String huisnummer;
    public String naam;
    public String postcode;
    public int telefoonnummer;


    public Locatie(int locatienr, String plaats, String straatnaam, String huisnummer, String naam, String postcode, int telefoonnummer) {
        this.locatienr = locatienr;
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.naam = naam;
        this.postcode = postcode;
        this.telefoonnummer = telefoonnummer;
    }

    public Locatie(int locatienr, String plaats, String straatnaam, String huisnummer) {
        this.locatienr = locatienr;
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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

    public int getLocatienr() {
        return locatienr;
    }

    public void setLocatienr(int locatienr) {
        this.locatienr = locatienr;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
}
