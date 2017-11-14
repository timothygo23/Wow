package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.PotionType;
import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Potion extends Equipment{

    public static final String TABLE_NAME = "potion";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STAT = "stat";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_POTIONTYPE = "potion_type";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIME = "time";

    private PotionType potionType;
    private int amount;
    private int time;

    public Potion(int id, String name, String description, Stat stat, int price, int amount, int time, PotionType potionType) {
        super(id, name, description, stat, price);
        this.potionType = potionType;
        this.amount = amount;
        this.time = time;
    }

    public PotionType getPotionType(){
        return potionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
