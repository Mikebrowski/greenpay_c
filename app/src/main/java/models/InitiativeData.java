package models;

import java.io.Serializable;

public class InitiativeData  implements Serializable {

    private String name;
    private String points;
    //private String iniativeName;

    public InitiativeData(String name, String points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}

