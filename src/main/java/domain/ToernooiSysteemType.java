package domain;

/**
 * Created by Peter-Paul on 21/04/2016.
 */
public class ToernooiSysteemType {
    private int id;
    private String systeem;
    private String type;

    public ToernooiSysteemType(int id, String systeem, String type) {
        this.id = id;
        this.systeem = systeem;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSysteem() {
        return systeem;
    }

    public void setSysteem(String systeem) {
        this.systeem = systeem;
    }
}
