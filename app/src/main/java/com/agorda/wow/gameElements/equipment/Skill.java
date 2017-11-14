package com.agorda.wow.gameElements.equipment;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Skill {

    public static final String TABLE_NAME = "armor";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAMAGE = "damage";
    public static final String COLUMN_MANA_COST = "mana_cost";
    public static final String COLUMN_BONUS = "bonus";

    private String name;
    private int damage, manaCost;
    private int bonus;

    public Skill(String name, int damage, int manaCost){
        this.name = name;
        this.damage =damage;
        this.manaCost = manaCost;
        bonus = 0;
    }

    public void updateDamage(int bonus){
        //add sa bonus
        this.bonus = bonus;
    }

    public void resetBonus() {
        bonus = 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage + bonus;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getBonus() {
        return bonus;
    }
}
