package domain;

/**
 * Created by dirk on 3-6-2016.
 */
public class SpelerInPoule {
    private int deelnemernr;
    private int deeltoernooinr;
    private int poulenr;
    private int gewonnenWedstrijden;
    private int gewonnenRondes;
    private int puntenVoor;
    private int puntenTegen;

    public SpelerInPoule(int deelnemernr, int deeltoernooinr, int poulenr, int gewonnenWedstrijden, int gewonnenRondes, int puntenVoor, int puntenTegen) {
        this.deelnemernr = deelnemernr;
        this.deeltoernooinr = deeltoernooinr;
        this.poulenr = poulenr;
        this.gewonnenWedstrijden = gewonnenWedstrijden;
        this.gewonnenRondes = gewonnenRondes;
        this.puntenVoor = puntenVoor;
        this.puntenTegen = puntenTegen;
    }

    public int getDeelnemernr() {
        return deelnemernr;
    }

    public void setDeelnemernr(int deelnemernr) {
        this.deelnemernr = deelnemernr;
    }

    public int getDeeltoernooinr() {
        return deeltoernooinr;
    }

    public void setDeeltoernooinr(int deeltoernooinr) {
        this.deeltoernooinr = deeltoernooinr;
    }

    public int getPoulenr() {
        return poulenr;
    }

    public void setPoulenr(int poulenr) {
        this.poulenr = poulenr;
    }

    public int getGewonnenWedstrijden() {
        return gewonnenWedstrijden;
    }

    public void setGewonnenWedstrijden(int gewonnenWedstrijden) {
        this.gewonnenWedstrijden = gewonnenWedstrijden;
    }

    public int getGewonnenRondes() {
        return gewonnenRondes;
    }

    public void setGewonnenRondes(int gewonnenRondes) {
        this.gewonnenRondes = gewonnenRondes;
    }

    public int getPuntenVoor() {
        return puntenVoor;
    }

    public void setPuntenVoor(int puntenVoor) {
        this.puntenVoor = puntenVoor;
    }

    public int getPuntenTegen() {
        return puntenTegen;
    }

    public void setPuntenTegen(int puntenTegen) {
        this.puntenTegen = puntenTegen;
    }
}
