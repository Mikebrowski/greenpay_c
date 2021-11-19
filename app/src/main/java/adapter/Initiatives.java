package adapter;

public class Initiatives {
    private String points;
    private int image; // JAVA SEEES IMAGES AS INT
    private String name;

    public Initiatives(String name, int image, String points){
        this.name= name;
        this.image = image;
        this.points = points;
    }
/*
    public Initiatives() {

    }
*/
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
