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
    private int tafelnr;
    private ArrayList<SpelerInWedstrijd> speler1;
    private ArrayList<SpelerInWedstrijd> speler2;


    public Wedstrijd(int deeltoernooinr, int teWinnenRondes, int wedstrijdnr, LocalDateTime startTijdDeeltoernooi, int tafelnr, ArrayList<SpelerInWedstrijd> speler1, ArrayList<SpelerInWedstrijd> speler2) {
        this.deeltoernooinr = deeltoernooinr;
        this.teWinnenRondes = teWinnenRondes;
        this.wedstrijdnr = wedstrijdnr;
        this.startTijdDeeltoernooi = startTijdDeeltoernooi;
        this.tafelnr = tafelnr;
        this.speler1 = speler1;
        this.speler2 = speler2;
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

    public int getTafelnr() {
        return tafelnr;
    }

    public void setTafelnr(int tafelnr) {
        this.tafelnr = tafelnr;
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
}
