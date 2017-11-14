package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Armor extends Equipment{

    public static final String TABLE_NAME = "armor";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STAT = "stat";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_HP = "hp";
    public static final String COLUMN_MP = "mp";
    public static final String COLUMN_STATVAL = "stat_value";

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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }
}
