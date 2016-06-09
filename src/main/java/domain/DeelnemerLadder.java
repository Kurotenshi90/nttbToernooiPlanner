package domain;

/**
 * Created by donnyolijslager on 07-06-16.
 */
public class DeelnemerLadder extends Deelnemer {
    private int rank;

    public DeelnemerLadder(int deelnemerID, int deelToernooinr, String voornaam, String achternaam, int bondsnr, String geslacht, String licentie, int verenigingnr, int rank) {
        super(deelnemerID, deelToernooinr, voornaam, achternaam, bondsnr, geslacht, licentie, verenigingnr);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
