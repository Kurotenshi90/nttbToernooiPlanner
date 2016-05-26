package presentation.models;

import domain.Toernooi;
import services.ToernooiService;

/**
 * Created by donnyolijslager on 26-05-16.
 */
public class DeeltoernooiSelecterenModel {
    private ToernooiService toernooiService;
    private Toernooi toernooi;

    public DeeltoernooiSelecterenModel(Toernooi toernooi) {
        this.toernooi = toernooi;
    }
}
