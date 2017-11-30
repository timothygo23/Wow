package com.agorda.wow.gameElements.db_constants;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Skill;
import com.agorda.wow.gameElements.equipment.Weapon;
import com.agorda.wow.gameElements.town.Town;
import com.agorda.wow.gameElements.types.PotionType;
import com.agorda.wow.gameElements.types.Stat;
import com.agorda.wow.util.DatabaseHelper;

/**
 * Created by Timothy on 14/11/2017.
 */

public class ObjectCreation {
    public static int ENEMY_COUNT = 3;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public ObjectCreation(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public void populateDb(SQLiteDatabase db){
        this.db = db;
        createWeapons();
        createSkills();
        createArmors();
        createPotions();
        createTowns();
        createEnemies();
        Log.i("DATABASE", "DONE POPULATING");
    }

    private void createWeapons(){
        databaseHelper.addWeapon(db, new Weapon(ObjectId.WOODEN_SWORD, "Wooden Sword", "Just a normal wooden sword.", Stat.STR, 1),
                ObjectId.BASIC_HACK,
                ObjectId.BASIC_SLASH,
                ObjectId.BASIC_DICE,
                ObjectId.BASIC_SLICE);

        databaseHelper.addWeapon(db, new Weapon(ObjectId.GOLDEN_SWORD, "Golden Sword", "Super rare special one of a kind sword.", Stat.STR, 999),
                ObjectId.SPECIAL_HACK,
                ObjectId.SPECIAL_SLASH,
                ObjectId.SPECIAL_DICE,
                ObjectId.SPECIAL_SLICE);
    }

    private void createArmors(){
        databaseHelper.addArmor(db, new Armor(ObjectId.WOODEN_HELMET, "Wooden Helmeet", "Noting special.", Stat.STR, 2, 1, 10, 5));
        databaseHelper.addArmor(db, new Armor(ObjectId.GOLDEN_HELMET, "Golden Helmeet", "Clearly a hacked item.", Stat.STR, 999, 999, 999, 999));
    }

    private void createPotions(){
        databaseHelper.addPotion(db, new Potion(ObjectId.HP_REGEN, "HP Regeneration", "Regain health points over time.", Stat.HP, 2, 10, 5, PotionType.REGEN));
        databaseHelper.addPotion(db, new Potion(ObjectId.MP_REGEN, "MP Regeneration", "Regain mana points over time.", Stat.MP, 2, 10, 5, PotionType.REGEN));
    }

    private void createSkills(){
        databaseHelper.addSkill(db, new Skill(ObjectId.BASIC_HACK, "HACK", 2, 1));
        databaseHelper.addSkill(db, new Skill(ObjectId.BASIC_SLASH, "SLASH", 4, 2));
        databaseHelper.addSkill(db, new Skill(ObjectId.BASIC_DICE, "DICE", 6, 3));
        databaseHelper.addSkill(db, new Skill(ObjectId.BASIC_SLICE, "SLICE", 8, 4));

        databaseHelper.addSkill(db, new Skill(ObjectId.SPECIAL_HACK, "EPIC HACK", 999, 0));
        databaseHelper.addSkill(db, new Skill(ObjectId.SPECIAL_SLASH, "EPIC SLASH", 999, 0));
        databaseHelper.addSkill(db, new Skill(ObjectId.SPECIAL_DICE, "EPIC DICE", 999, 0));
        databaseHelper.addSkill(db, new Skill(ObjectId.SPECIAL_SLICE, "EPIC SLICE", 999, 0));
    }

    private void createTowns(){
        databaseHelper.addTown(db, new Town(ObjectId.START_TOWN, "West Town", "Kinda average for a starting town.", 0));
        databaseHelper.addTown(db, new Town(ObjectId.SECOND_TOWN, "North Town", "It's pretty cold in this place.", 10));
    }

    private void createEnemies(){
        databaseHelper.addEnemy(db, new Enemy(ObjectId.SLIME, "Slime", 10, 3));
        databaseHelper.addEnemy(db, new Enemy(ObjectId.BLUE_SLIME, "Blue Slime", 15, 5));
        databaseHelper.addEnemy(db, new Enemy(ObjectId.RED_SLIME, "Red Slime", 20, 6));
    }
}
