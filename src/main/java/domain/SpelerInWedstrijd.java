package domain;

/**
 * Created by donnyolijslager on 01-06-16.
 */
public class SpelerInWedstrijd {
    private String voornaam;
    private String achternaam;
    private int bondsnr;

    public SpelerInWedstrijd(String voornaam, String achternaam, int bondsnr) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.bondsnr = bondsnr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getBondsnr() {
        return bondsnr;
    }

    public void setBondsnr(int bondsnr) {
        this.bondsnr = bondsnr;
    }
}
