package com.agorda.wow.gameElements.player;

import android.util.Log;

import com.agorda.wow.gameElements.town.Town;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Destination {
    private Town from;
    private Town destination;
    private int steps;
    private int stepsNeeded;

    public Destination(Town from, Town to){
        this.from = from;
        this.destination = to;

        steps = 0;
        stepsNeeded = Math.round(Math.abs(to.getLocation() - from.getLocation())) * 500;

    }

    public boolean addStep(int a){
        steps += a;

        if(stepsNeeded != steps)
            return true;
        else
            return false;
    }

    public int getStepsNeeded(){
        return stepsNeeded;
    }

    public int getSteps(){
        return steps;
    }

    public Town getNextTown(){
        return destination;
    }
}
