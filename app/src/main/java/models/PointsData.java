package models;

import java.util.Date;

public class PointsData {

    public Integer totalpoints;
    public String username;
    public String initiativeName;
    public Date currentDateS;

    public PointsData(Integer totalpoints, String username, String initiativeName, Date currentDateS) {
        this.totalpoints = totalpoints;
        this.username = username;
        this.initiativeName = initiativeName;
        this.currentDateS = currentDateS;
    }

    public Integer getTotalpoints() { return totalpoints; }
    public void setTotalpoints(Integer totalpoints) { this.totalpoints = totalpoints; }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getInitiativeName() { return initiativeName; }
    public void setInitiativeName(String initiativeName) { this.initiativeName = initiativeName; }
    public Date getCurrentDateS() { return currentDateS; }
    public void setCurrentDateS(Date currentDateS) { this.currentDateS = currentDateS; }

}