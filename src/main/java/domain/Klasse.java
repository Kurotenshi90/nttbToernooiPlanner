package domain;

/**
 * Created by donnyolijslager on 17-05-16.
 */
public class Klasse {
    private String licentietype;
    private String klassennaam;


    public Klasse(String licentietype, String klassennaam) {
        this.licentietype = licentietype;
        this.klassennaam = klassennaam;
    }

    public String getLicentietype() {
        return licentietype;
    }

    public void setLicentietype(String licentietype) {
        this.licentietype = licentietype;
    }

    public String getKlassennaam() {
        return klassennaam;
    }

    public void setKlassennaam(String klassennaam) {
        this.klassennaam = klassennaam;
    }
}
