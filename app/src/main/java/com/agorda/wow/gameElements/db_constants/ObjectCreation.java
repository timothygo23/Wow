package com.agorda.wow.gameElements.db_constants;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.agorda.wow.R;
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
        databaseHelper.addWeapon(db, new Weapon(ObjectId.DAGGER, "Dagger", "Just a normal dagger.", Stat.AGI, 15),
                ObjectId.DAGGER_SKILL1,
                ObjectId.DAGGER_SKILL2,
                ObjectId.DAGGER_SKILL3);

        databaseHelper.addWeapon(db, new Weapon(ObjectId.HAMMER, "Hammer", "Just a normal hammer.", Stat.STR, 15),
                ObjectId.HAMMER_SKILL1,
                ObjectId.HAMMER_SKILL2,
                ObjectId.HAMMER_SKILL3);
    }

    private void createArmors(){
        databaseHelper.addArmor(db, new Armor(ObjectId.HAT, "Witch's Hat", "Light and fast.", Stat.AGI, 2, 15, 10, 5));
        databaseHelper.addArmor(db, new Armor(ObjectId.STEEL_ARMOUR, "Steel Armour", "Heavy and powerful.", Stat.STR, 4, 25, 15, 7));
    }

    private void createPotions(){
        databaseHelper.addPotion(db, new Potion(ObjectId.HP_REGEN, "HP Regeneration", "Regain health points over time.", Stat.HP, 2, 10, 5, PotionType.REGEN));
        databaseHelper.addPotion(db, new Potion(ObjectId.MP_REGEN, "MP Regeneration", "Regain mana points over time.", Stat.MP, 2, 10, 5, PotionType.REGEN));
    }

    private void createSkills(){
        databaseHelper.addSkill(db, new Skill(ObjectId.DAGGER_SKILL1, "SLICE", 4, 2));
        databaseHelper.addSkill(db, new Skill(ObjectId.DAGGER_SKILL2, "POISON???", 6, 3));
        databaseHelper.addSkill(db, new Skill(ObjectId.DAGGER_SKILL3, "NIGHT CRAWLER", 8, 4));

        databaseHelper.addSkill(db, new Skill(ObjectId.HAMMER_SKILL1, "JUDGEMENT", 4, 2));
        databaseHelper.addSkill(db, new Skill(ObjectId.HAMMER_SKILL2, "SMASH", 6, 3));
        databaseHelper.addSkill(db, new Skill(ObjectId.HAMMER_SKILL3, "SCREAM", 8, 4));
    }

    private void createTowns(){
        databaseHelper.addTown(db, new Town(ObjectId.START_TOWN, "West Town", "Kinda average for a starting town.", 0));
        databaseHelper.addTown(db, new Town(ObjectId.SECOND_TOWN, "North Town", "It's pretty cold in this place.", 10));
    }

    private void createEnemies(){
        databaseHelper.addEnemy(db, new Enemy(ObjectId.PYRO, "Pyro", 20, 5));
        databaseHelper.addEnemy(db, new Enemy(ObjectId.PLANT, "Sunflower", 15, 5));
        databaseHelper.addEnemy(db, new Enemy(ObjectId.GHOST, "Ghost", 20, 6));
    }
}
