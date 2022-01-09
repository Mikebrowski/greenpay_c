package models;

public class InitiativeDbGoals {
    String name;
    String points;
    String type;
    String imgpath;

    public InitiativeDbGoals(String name, String points, String type, String imgpath) {
        this.name = name;
        this.points = points;
        this.type = type;
        this.imgpath = imgpath;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgpath;
    }

    public void setImgPath(String imgpath) {
        this.imgpath = imgpath;
    }


}
