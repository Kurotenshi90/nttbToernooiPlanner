package presentation.models;

import domain.Commissielid;
import services.CommissieService;

/**
 * Created by dirk on 31-5-2016.
 */
public class CommissieModel {
    CommissieService commissieService;

    public CommissieModel() {
        commissieService = new CommissieService();
    }

    public void saveCommissielid(Commissielid commissielid){
        commissieService.saveCommissielid(commissielid);
    }
}
