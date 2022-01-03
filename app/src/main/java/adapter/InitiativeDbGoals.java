package adapter;

public class InitiativeDbGoals {
    String name;
    Double points;
    String type;
    String imgPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public InitiativeDbGoals(String name, Double points, String type, String imgPath) {
        this.name = name;
        this.points = points;
        this.type = type;
        this.imgPath = imgPath;
    }


}
