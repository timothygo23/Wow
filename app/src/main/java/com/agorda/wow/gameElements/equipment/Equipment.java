package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Equipment {
    private int id;
    private String name;
    private String description;
    private Stat stat;

    private int price;

    public Equipment(int id, String name, String description, Stat stat, int price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.stat = stat;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId(){
        return id;
    }
}
