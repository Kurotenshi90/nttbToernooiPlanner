package domain;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class Klasse {
    private String licentietype;
    private String klassenaam;


    public Klasse(String licentietype, String klassennaam) {
        this.licentietype = licentietype;
        this.klassenaam = klassennaam;
    }

    public String getLicentietype() {
        return licentietype;
    }

    public void setLicentietype(String licentietype) {
        this.licentietype = licentietype;
    }

    public String getKlassenaam() {
        return klassenaam;
    }

    public void setKlassenaam(String klassennaam) {
        this.klassenaam = klassennaam;
    }

    public String toString() {
        return "Klasse: " + klassenaam + " " + licentietype;
    }
}
