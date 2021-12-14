package adapter;

public class PointsData {

    private Integer totalpoints;
    private String username;
    private String initiativeName;
    private String currentDateS;

    public PointsData(Integer totalpoints, String username, String initiativeName, String currentDateS) {
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
    public String getCurrentDateS() { return currentDateS; }
    public void setCurrentDateS(String currentDateS) { this.currentDateS = currentDateS; }

}