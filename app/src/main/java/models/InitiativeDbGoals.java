package models;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitiativeDbGoals that = (InitiativeDbGoals) o;
        return name.equals(that.name) && points.equals(that.points) && type.equals(that.type) && imgpath.equals(that.imgpath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, points, type, imgpath);
    }

}
