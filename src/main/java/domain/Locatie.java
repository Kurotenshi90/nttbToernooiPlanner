package domain;

/**
 * Created by Peter-Paul on 20/04/2016.
 */
public class Locatie {
    public int locatienr;
    public String plaats;
    public String straatnaam;
    public String huisnummer;

    public Locatie(int locatienr, String plaats, String straatnaam, String huisnummer) {
        this.locatienr = locatienr;
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
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
