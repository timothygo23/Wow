package com.agorda.wow.util;

import android.content.SharedPreferences;
import android.util.Log;

import com.agorda.wow.Fight;
import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Equipment;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Weapon;
import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Timothy on 14/11/2017.
 */

public class GameSave {

    public static void save(SharedPreferences.Editor dspEditor, Player player){
        //player info
        dspEditor.putString ("name", player.getData().getName());
        dspEditor.putInt("level", player.getData().getLevel());
        dspEditor.putInt("XP", player.getData().getXP());
        dspEditor.putInt("gold", player.getData().getGold());
        dspEditor.putInt("HP", player.getData().getHP());
        dspEditor.putInt("MP", player.getData().getMP());
        dspEditor.putInt("maxHP", player.getData().getMaxHP());
        dspEditor.putInt("maxMP", player.getData().getMaxMP());

        //player state
        dspEditor.putString("state", player.getState().toString());

        //destination related
        if(player.getDestination() != null){
            dspEditor.putInt("steps", player.getDestination().getSteps());
            dspEditor.putInt("destination", player.getDestination().getNextTown().getId());
        }

        //current town
        dspEditor.putInt("currentTown", player.getCurrentTown().getId());

        //equipped wep, armor, potion id
        dspEditor.putInt("weapon", player.getData().getWeapon().getId());
        dspEditor.putInt("armor", player.getData().getArmor().getId());
        if(player.getData().getPotion() != null)
            dspEditor.putInt("potion", player.getData().getPotion().getId());

        //inventory wep, armor, potion id
        ArrayList<String> weapons = new ArrayList<String>();
        ArrayList<String> armors = new ArrayList<String>();
        ArrayList<String> potions = new ArrayList<String>();

        for(Equipment e : player.getItems()){
            if(e instanceof Weapon){
                weapons.add(e.getId() + "");
            }else if(e instanceof Armor){
                armors.add(e.getId() + "");
            }else if(e instanceof Potion){
                potions.add(e.getId() + "");
            }
        }

        dspEditor.putStringSet("weaponInventory", new HashSet<String>(weapons));
        dspEditor.putStringSet("armorInventory", new HashSet<String>(armors));
        dspEditor.putStringSet("potionInventory", new HashSet<String>(potions));

        dspEditor.apply ();
    }

    public static Player loadPlayer(SharedPreferences dsp, DatabaseHelper databaseHelper){

        Player player = new Player(dsp.getString("name",""),
                databaseHelper.getTown(dsp.getInt("currentTown", ObjectId.START_TOWN)),
                        PlayerState.valueOf(dsp.getString("state", "TOWN")));

        //equipment
        player.equip(databaseHelper.getWeapon(dsp.getInt("weapon", ObjectId.DAGGER)));
        player.equip(databaseHelper.getArmor(dsp.getInt("armor", ObjectId.HAT)));
        int potion = dsp.getInt("potion", -1);
        if(potion != -1){
            player.equip(databaseHelper.getPotion(potion));
        }

        player.getData().setLevel(dsp.getInt("level", 1));
        player.getData().setXP(dsp.getInt("XP", 1));
        player.getData().setGold(dsp.getInt("gold", 1));
        player.getData().setHP(dsp.getInt("HP", 15));
        player.getData().setMP(dsp.getInt("MP", 15));
        player.getData().setMaxHP(dsp.getInt("maxHP", 15));
        player.getData().setMaxMP(dsp.getInt("maxMP", 15));

        //destination
        int destination = dsp.getInt("destination", -1);
        if(destination != -1){
            //there's a destination
            Destination d = new Destination(player.getCurrentTown(), databaseHelper.getTown(destination));
            d.addStep(dsp.getInt("steps", 0));
            player.setDestination(d);
        }

        //inventory
        ArrayList<String> weaponIDs = new ArrayList<String>(dsp.getStringSet("weaponInventory", null));
        ArrayList<String> armorIDs = new ArrayList<String>(dsp.getStringSet("armorInventory", null));
        ArrayList<String> potionIDs = new ArrayList<String>(dsp.getStringSet("armorInventory", null));

        for(String s : weaponIDs){
            player.getItems().add(databaseHelper.getWeapon(Integer.parseInt(s)));
        }
        for(String s : armorIDs){
            player.getItems().add(databaseHelper.getArmor(Integer.parseInt(s)));
        }
        for(String s : potionIDs){
            player.getItems().add(databaseHelper.getPotion(Integer.parseInt(s)));
        }

        return player;
    }

    public static void changePlayerState(SharedPreferences.Editor dspEditor, PlayerState playerState){
        dspEditor.putString("state", playerState.toString());
        dspEditor.apply();
    }

    public static void changeStepCount(SharedPreferences.Editor dspEditor, int steps){
        dspEditor.putInt("steps", steps);
        dspEditor.apply();
    }

    public static void saveEnemy(SharedPreferences.Editor dspEditor, Enemy enemy){
        dspEditor.putInt("enemy_id", enemy.getId());
        dspEditor.putString("enemy_name", enemy.getName());
        dspEditor.putInt("enemy_hp", enemy.getHp());
        dspEditor.putInt("enemy_dmg", enemy.getDmg());

        dspEditor.apply();
    }

    public static Enemy loadEnemy(SharedPreferences dsp, DatabaseHelper databaseHelper){
        Enemy enemy = new Enemy(dsp.getInt("enemy_id", 1),
                dsp.getString("enemy_name", ""),
                dsp.getInt("enemy_hp", 1),
                dsp.getInt("enemy_dmg", 1));

        return enemy;
    }

    public static void saveTurn(SharedPreferences.Editor editor, int value){
        editor.putInt(Fight.TURN, value);
        editor.commit();
    }

    public static int getTurn(SharedPreferences dsp){
        return dsp.getInt(Fight.TURN, Fight.PLAYER_TURN);
    }
}
