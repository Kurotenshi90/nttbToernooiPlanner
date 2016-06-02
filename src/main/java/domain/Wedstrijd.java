package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Peter-Paul on 31/05/2016.
 */
public class Wedstrijd {
    private int deeltoernooinr;
    private int teWinnenRondes;
    private int wedstrijdnr;
    private LocalDateTime startTijdDeeltoernooi;
    private Tafel tafel;
    private ArrayList<SpelerInWedstrijd> speler1;
    private ArrayList<SpelerInWedstrijd> speler2;


    public Wedstrijd(int deeltoernooinr, int teWinnenRondes, int wedstrijdnr, LocalDateTime startTijdDeeltoernooi) {
        this.deeltoernooinr = deeltoernooinr;
        this.teWinnenRondes = teWinnenRondes;
        this.wedstrijdnr = wedstrijdnr;
        this.startTijdDeeltoernooi = startTijdDeeltoernooi;
        this.speler1 = new ArrayList<>();
        this.speler2 = new ArrayList<>();
    }

    public int getDeeltoernooinr() {
        return deeltoernooinr;
    }

    public void setDeeltoernooinr(int deeltoernooinr) {
        this.deeltoernooinr = deeltoernooinr;
    }

    public int getTeWinnenRondes() {
        return teWinnenRondes;
    }

    public void setTeWinnenRondes(int teWinnenRondes) {
        this.teWinnenRondes = teWinnenRondes;
    }

    public LocalDateTime getStartTijdDeeltoernooi() {
        return startTijdDeeltoernooi;
    }

    public void setStartTijdDeeltoernooi(LocalDateTime startTijdDeeltoernooi) {
        this.startTijdDeeltoernooi = startTijdDeeltoernooi;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public void removeTafel(){
        tafel = null;
    }

    public int getWedstrijdnr() {
        return wedstrijdnr;
    }

    public void setWedstrijdnr(int wedstrijdnr) {
        this.wedstrijdnr = wedstrijdnr;
    }

    public ArrayList<SpelerInWedstrijd> getSpeler1() {
        return speler1;
    }

    public void setSpeler1(ArrayList<SpelerInWedstrijd> speler1) {
        this.speler1 = speler1;
    }

    public ArrayList<SpelerInWedstrijd> getSpeler2() {
        return speler2;
    }

    public void setSpeler2(ArrayList<SpelerInWedstrijd> speler2) {
        this.speler2 = speler2;
    }

    public void addSpeler1(SpelerInWedstrijd spelerInWedstrijd){
        speler1.add(spelerInWedstrijd);
    }

    public void addSpeler2(SpelerInWedstrijd spelerInWedstrijd){
        speler2.add(spelerInWedstrijd);
    }
}
