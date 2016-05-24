package presentation.models;

import domain.Locatie;
import domain.Vereniging;
import services.LocatieService;
import services.VerenigingService;

/**
 * Created by donnyolijslager on 13-05-16.
 */
public class VerenigingToevoegenModel {
    VerenigingService verenigingService;

    public VerenigingToevoegenModel() {
        verenigingService = new VerenigingService();
    }

    public void saveVereniging(Vereniging vereniging){
        verenigingService.saveVereniging(vereniging);
    }
}
