package domain;

/**
 * Created by Peter-Paul on 20/04/2016.
 */
public class Locatie {
    public String plaats;
    public String straatnaam;
    public String huisnummer;

    public Locatie(String plaats, String straatnaam, String huisnummer) {
        this.plaats = plaats;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
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
