package domain;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by Peter-Paul on 21/04/2016.
 */
public class CommisieLidInToernooi {
    private String naam;
    private int lidnr;
    private SimpleBooleanProperty leider = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty contactpersoon = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty bondsVertegenwoordiger = new SimpleBooleanProperty(false);


    public CommisieLidInToernooi() {

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLidnr() {
        return lidnr;
    }

    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
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

    public SimpleBooleanProperty bondsVertegenwoordigerProperty(){
        return this.bondsVertegenwoordiger;
    }

    public Boolean getContactpersoon() {
        return this.contactpersoonProperty().get();
    }

    public void setContactpersoon(Boolean contactpersoon) {
        this.contactpersoonProperty().set(contactpersoon);
    }

    public Boolean getBondsVertegenwoordiger() {
        return this.bondsVertegenwoordigerProperty().get();
    }

    public void setBondsVertegenwoordiger(Boolean bondsVertegenwoordiger) {
        this.bondsVertegenwoordigerProperty().set(bondsVertegenwoordiger);
    }



}
