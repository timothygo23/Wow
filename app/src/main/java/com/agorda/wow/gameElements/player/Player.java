package com.agorda.wow.gameElements.player;

import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Equipment;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Weapon;

import java.util.ArrayList;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Player {
    private PlayerData data;
    private Destination destination;
    private ArrayList<Equipment> items;

    public Player(String name){
        data = new PlayerData(name);
        items = new ArrayList<Equipment>();
    }

    public void setDestination(Destination destination){
        this.destination = destination;
    }

    public boolean walk(){
        return destination.addStep(1);
    }

    public void equip(Equipment e){
        if(e instanceof Weapon){
            //reset
            if(data.getWeapon() != null)
                data.getWeapon().resetSkillBonus();
            //equip
            data.setWeapon((Weapon)e);
            //update skill stat
            if(data.getArmor() != null)
                data.getWeapon().updateSkillsDamage(data.getArmor());
        }else if(e instanceof Armor){
            data.setArmor((Armor)e);
            //update skill stat
            if(data.getWeapon() != null)
                data.getWeapon().updateSkillsDamage(data.getArmor());
        }else if(e instanceof Potion){
            data.setPotion((Potion)e);
        }
    }

    public PlayerData getData(){
        return data;
    }

    public ArrayList<Equipment> getItems(){
        return items;
    }

    public Destination getDestination(){
        return destination;
    }
}
