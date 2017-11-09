package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Armor extends Equipment{
    private int HP, MP;
    private int statValue;

    public Armor(String name, String description, Stat stat, int statValue, int price, int HP, int MP) {
        super(name, description, stat, price);
        this.statValue = statValue;
        this.HP = HP;
        this.MP = MP;
    }

    public int getStatValue() {
        return statValue;
    }

}
