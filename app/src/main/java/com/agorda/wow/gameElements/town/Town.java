package com.agorda.wow.gameElements.town;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Town {
    private String name;
    private Town adjacentLeft, adjacentRight;
    private float location; //in kilometers

    public Town(String name, float location){
        this.name = name;
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
}
