package adapter;

public class PointsData {

    //private String points;
    private Integer totalpoints;
    private String username;

    //private String initiativeName;

    public PointsData(Integer totalpoints, String username) {
        this.totalpoints = totalpoints;
        this.username = username;
    }

    public Integer getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(Integer totalpoints) {
        this.totalpoints = totalpoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}