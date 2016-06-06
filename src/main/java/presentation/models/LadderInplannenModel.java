package presentation.models;

import domain.Toernooi;
import services.ToernooiService;

/**
 * Created by dirk on 6-6-2016.
 */
public class LadderInplannenModel {

    private ToernooiService toernooiService;
    private int deeltoernooinummer;
    private Toernooi toernooi;

    public LadderInplannenModel(Toernooi toernooi, int deeltoernooinummer) {
        this.toernooi = toernooi;
        this.deeltoernooinummer = deeltoernooinummer;
        this.toernooiService = new ToernooiService();
    }

    public Toernooi getToernooi(){
        return toernooi;
    }
}
