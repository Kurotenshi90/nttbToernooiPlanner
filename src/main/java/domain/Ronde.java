package domain;

/**
 * Created by donnyolijslager on 02-06-16.
 */
public class Ronde {
    private int scoreA;
    private int scoreB;
    private int Rondenr;
    private int wedstrijdnr;

    public Ronde(int scoreA, int scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public int getWedstrijdnr() {
        return wedstrijdnr;
    }

    public void setWedstrijdnr(int wedstrijdnr) {
        this.wedstrijdnr = wedstrijdnr;
    }

    public int getRondenr() {
        return Rondenr;
    }

    public void setRondenr(int rondenr) {
        Rondenr = rondenr;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }
}
