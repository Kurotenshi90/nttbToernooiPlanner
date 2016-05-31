package presentation.models;

import domain.Tafel;
import services.WedstrijdenService;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 30/05/2016.
 */
public class WedstrijdenInplannenModel {
    private int toernooiId;
    private ArrayList<Tafel> tafels;
    private WedstrijdenService wedstrijdenService;

    public WedstrijdenInplannenModel(int toernooiId) {
        this.toernooiId = toernooiId;
        wedstrijdenService = new WedstrijdenService();
        setTafels();
    }

    public ArrayList<Tafel> tafels(){
        return  tafels;
    }

    public void setTafels(){
        tafels = wedstrijdenService.getNietBezetteTafels(toernooiId);
    }
    public void addTafels(int aantal){
        wedstrijdenService.addTafels(toernooiId, aantal);
        setTafels();
    }
}
