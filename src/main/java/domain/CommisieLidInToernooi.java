package domain;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by Peter-Paul on 21/04/2016.
 */
public class CommisieLidInToernooi {
    private String voornaam;
    private String achternaam;
    private SimpleBooleanProperty leider = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty contactpersoon = new SimpleBooleanProperty(false);


    public CommisieLidInToernooi(String voornaam, String achternaam) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
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

    public SimpleBooleanProperty leiderProperty(){
        return this.leider;
    }

    public Boolean getLeider() {
        return this.leiderProperty().get();
    }

    public void setLeider(Boolean leider) {
        this.leiderProperty().set(leider);
    }

    public SimpleBooleanProperty contactpersoonProperty(){
        return this.contactpersoon;
    }

    public Boolean getContactpersoon() {
        return this.contactpersoonProperty().get();
    }

    public void setContactpersoon(Boolean contactpersoon) {
        this.contactpersoonProperty().set(contactpersoon);
    }

}
