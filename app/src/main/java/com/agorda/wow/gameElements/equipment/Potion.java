package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.PotionType;
import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Potion extends Equipment{
    private PotionType potionType;
    private int amount;
    private int time;

    public Potion(String name, String description, Stat stat, int price, int amount, int time, PotionType potionType) {
        super(name, description, stat, price);
        this.potionType = potionType;
        this.amount = amount;
        this.time = time;
    }

    public PotionType getPotionType(){
        return potionType;
    }

}
