package models;

public class InitiativeDbGoals {
    String name;
    String points;
    String type;
    String imgpath;
    String description;


    public String getDescription() {
        return description;
    }

    public InitiativeDbGoals(){}

    public InitiativeDbGoals(String name, String points, String type, String imgPath, String description) {
        this.name = name;
        this.points = points;
        this.type = type;
        this.imgpath = imgPath;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }



}
