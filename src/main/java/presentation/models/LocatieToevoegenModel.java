package presentation.models;

import domain.Locatie;
import services.LocatieService;

/**
 * Created by donnyolijslager on 13-05-16.
 */
public class LocatieToevoegenModel {
    LocatieService locatieService;

    public LocatieToevoegenModel() {
        locatieService = new LocatieService();
    }

    public void saveLocatie(Locatie locatie){
        locatieService.saveLocatie(locatie);
    }
}
