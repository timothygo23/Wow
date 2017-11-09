package com.agorda.wow.gameElements.player;

import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Weapon;

/**
 * Created by Timothy on 07/11/2017.
 */

public class PlayerData {
    private String name;
    private int level, XP;
    private int gold;

    private int HP, MP, maxHP, maxpMP;

    private Weapon weapon;
    private Armor armor;
    private Potion potion;

    public PlayerData(String name){
        this.name = name;
        level = 1;
        XP = 1;
        gold = 1;
        maxHP = HP = 15;
        maxpMP = MP = 15;

        //equip basic weapon
        //equip basic armor
        //equip basic potion
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxpMP() {
        return maxpMP;
    }

    public void setMaxpMP(int maxpMP) {
        this.maxpMP = maxpMP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }
}
