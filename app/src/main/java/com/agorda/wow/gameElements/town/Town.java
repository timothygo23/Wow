package com.agorda.wow.gameElements.town;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Town {

    public static final String TABLE_NAME = "Town";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LOCATION = "location";

    private String name, description;
    private Town adjacentLeft, adjacentRight;
    private float location; //in kilometers

    public Town(String name, String description, float location){
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public void setAdjacentTowns (Town left, Town right) {
        this.adjacentLeft = left;
        this.adjacentRight = right;
    }

    public float getLocation(){
        return location;
    }

    public Town getLeft() {
        return adjacentLeft;
    }

    public Town getRight() {
        return adjacentRight;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}
