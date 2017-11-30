package com.agorda.wow.gameElements.enemy;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Enemy {

    public static String TABLE_NAME = "enemy";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_HP = "hp";
    public static String COLUMN_DMG = "dmg";

    private String name;
    private int hp;
    private int dmg;

    private int _id;

    public Enemy(int _id, String name, int hp, int dmg){
        this._id = _id;
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getId(){
        return _id;
    }
}
