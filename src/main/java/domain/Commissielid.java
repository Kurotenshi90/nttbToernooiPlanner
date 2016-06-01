package domain;

/**
 * Created by dirk on 31-5-2016.
 */
public class Commissielid {
    public int lidnr;
    public String naam;
    public String straatnaam;
    public int huisnr;
    public String postcode;
    public String email;


    public Commissielid(int lidnr, String naam, String straatnaam, int huisnr, String postcode, String email) {
        this.lidnr = lidnr;
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.email = email;
    }

    public int getLidnr() {
        return lidnr;
    }

    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
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

    public int getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(int huisnr) {
        this.huisnr = huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
