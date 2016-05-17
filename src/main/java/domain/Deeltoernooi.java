package domain;

/**
 * Created by Peter-Paul on 17/05/2016.
 */
public class Deeltoernooi {
    private int maxAantalSpelers;
    private String spelvorm;
    public Deeltoernooi(String spelvorm, int maxAantalSpelers) {
        this.spelvorm = spelvorm;
        this.maxAantalSpelers = maxAantalSpelers;
    }

    public int getMaxAantalSpelers() {
        return maxAantalSpelers;
    }

    public void setMaxAantalSpelers(int maxAantalSpelers) {
        this.maxAantalSpelers = maxAantalSpelers;
    }

    public String getSpelvorm() {
        return spelvorm;
    }

    public void setSpelvorm(String spelvorm) {
        this.spelvorm = spelvorm;
    }
}
