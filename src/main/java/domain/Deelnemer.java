package domain;

/**
 * Created by donnyolijslager on 23-05-16.
 */
public class Deelnemer {
    private int deelnemerID;
    private int deelToernooinr;
    private String voornaam;
    private String achternaam;
    private int bondsnr;
    private String geslacht;
    private String licentie;
    private int bondsnrPartner;
    private int verenigingnr;
    private String deeltoernooi;

    public Deelnemer(int deelnemerID, int deelToernooinr, String voornaam, String achternaam, int bondsnr, String geslacht, String licentie, int verenigingnr) {
        this.deelnemerID = deelnemerID;
        this.deelToernooinr = deelToernooinr;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.bondsnr = bondsnr;
        this.geslacht = geslacht;
        this.licentie = licentie;
        this.verenigingnr = verenigingnr;
    }

    public Deelnemer(int deelnemerID, int deelToernooinr, String voornaam, String achternaam, int bondsnr, String geslacht, String licentie, int verenigingnr, int bondsnrPartner) {
        this.deelnemerID = deelnemerID;
        this.deelToernooinr = deelToernooinr;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.bondsnr = bondsnr;
        this.geslacht = geslacht;
        this.licentie = licentie;
        this.verenigingnr = verenigingnr;
        this.bondsnrPartner = bondsnrPartner;
    }

    public Deelnemer(int deelToernooinr, String voornaam, String achternaam, int bondsnr, String geslacht, String licentie, int bondsnrPartner, int verenigingnr, String deeltoernooi) {
        this.deelToernooinr = deelToernooinr;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.bondsnr = bondsnr;
        this.geslacht = geslacht;
        this.licentie = licentie;
        this.bondsnrPartner = bondsnrPartner;
        this.verenigingnr = verenigingnr;
        this.deeltoernooi = deeltoernooi;
    }

    public Deelnemer(int deelnemerID, int deelToernooinr, String voornaam, String achternaam, int bondsnr, String geslacht, String licentie, int bondsnrPartner, int verenigingnr, String deeltoernooi) {
        this.deelnemerID = deelnemerID;
        this.deelToernooinr = deelToernooinr;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.bondsnr = bondsnr;
        this.geslacht = geslacht;
        this.licentie = licentie;
        this.bondsnrPartner = bondsnrPartner;
        this.verenigingnr = verenigingnr;
        this.deeltoernooi = deeltoernooi;
    }

    public String getDeeltoernooi() {
        return deeltoernooi;
    }

    public void setDeeltoernooi(String deeltoernooi) {
        this.deeltoernooi = deeltoernooi;
    }

    public int getVerenigingnr() {
        return verenigingnr;
    }

    public void setVerenigingnr(int verenigingnr) {
        this.verenigingnr = verenigingnr;
    }

    public int getDeelnemerID() {
        return deelnemerID;
    }

    public void setDeelnemerID(int deelnemerID) {
        this.deelnemerID = deelnemerID;
    }

    public int getDeelToernooinr() {
        return deelToernooinr;
    }

    public void setDeelToernooinr(int deelToernooinr) {
        this.deelToernooinr = deelToernooinr;
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

    public int getBondsnr() {
        return bondsnr;
    }

    public void setBondsnr(int bondsnr) {
        this.bondsnr = bondsnr;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getLicentie() {
        return licentie;
    }

    public void setLicentie(String licentie) {
        this.licentie = licentie;
    }

    public int getBondsnrPartner() {
        return bondsnrPartner;
    }

    public void setBondsnrPartner(int bondsnrPartner) {
        this.bondsnrPartner = bondsnrPartner;
    }
}
