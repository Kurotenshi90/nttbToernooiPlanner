package domain;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by dirk on 7-6-2016.
 */
public class PouleOverzicht {
    private ArrayList<SpelerInPoule> spelerInPoules;
    private String naam;

    public PouleOverzicht(String naam) {
        spelerInPoules = new ArrayList<>();
        this.naam = naam;
    }

    public ArrayList<SpelerInPoule> getSpelerInPoules(){
        return spelerInPoules;
    }

    public void addSpelerInPoule(SpelerInPoule spelerInPoule) {
        spelerInPoules.add(spelerInPoule);
    }

    public String getNaam() {
        return naam;
    }
}
