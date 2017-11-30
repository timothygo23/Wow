package com.agorda.wow.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.agorda.wow.gameElements.db_constants.ObjectCreation;
import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Skill;
import com.agorda.wow.gameElements.equipment.Weapon;
import com.agorda.wow.gameElements.town.Town;
import com.agorda.wow.gameElements.types.PotionType;
import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Luigi on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "inventory";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String weaponSQL, armorSQL, potionSQL, townSQL, skillSQL, enemySQL;

        weaponSQL = "CREATE TABLE " + Weapon.TABLE_NAME + " ("
                + Weapon.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Weapon.COLUMN_NAME + " TEXT, "
                + Weapon.COLUMN_DESCRIPTION + " TEXT, "
                + Weapon.COLUMN_STAT + " TEXT, "
                + Weapon.COLUMN_PRICE + " INTEGER, "
                + Weapon.COLUMN_SKILL_ID_1 + " INTEGER, "
                + Weapon.COLUMN_SKILL_ID_2 + " INTEGER, "
                + Weapon.COLUMN_SKILL_ID_3 + " INTEGER, "
                + Weapon.COLUMN_SKILL_ID_4 + " INTEGER"
                + ");";

        skillSQL = "CREATE TABLE " + Skill.TABLE_NAME + " ("
                + Skill.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Skill.COLUMN_NAME + " TEXT, "
                + Skill.COLUMN_DAMAGE + " INTEGER, "
                + Skill.COLUMN_MANA_COST + " INTEGER"
                + ");";

        armorSQL = "CREATE TABLE " + Armor.TABLE_NAME + " ("
                + Armor.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Armor.COLUMN_NAME + " TEXT, "
                + Armor.COLUMN_DESCRIPTION + " TEXT, "
                + Armor.COLUMN_STAT + " TEXT, "
                + Armor.COLUMN_PRICE + " INTEGER, "
                + Armor.COLUMN_HP + " INTEGER,"
                + Armor.COLUMN_MP + " INTEGER, "
                + Armor.COLUMN_STATVAL + " INTEGER"
                + ");";

        potionSQL = "CREATE TABLE " + Potion.TABLE_NAME + " ("
                + Potion.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Potion.COLUMN_NAME + " TEXT, "
                + Potion.COLUMN_DESCRIPTION + " TEXT, "
                + Potion.COLUMN_STAT + " TEXT, "
                + Potion.COLUMN_PRICE + " INTEGER, "
                + Potion.COLUMN_POTIONTYPE + " TEXT, "
                + Potion.COLUMN_AMOUNT + " INTEGER, "
                + Potion.COLUMN_TIME + " INTEGER"
                + ");";

        townSQL = "CREATE TABLE " + Town.TABLE_NAME + " ("
                + Town.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Town.COLUMN_NAME + " TEXT, "
                + Town.COLUMN_DESCRIPTION + " TEXT, "
                + Town.COLUMN_LOCATION + " REAL"
                + ");";

        enemySQL = "CREATE TABLE " + Enemy.TABLE_NAME + " ("
                + Enemy.COLUMN_ID + " INTEGER PRIMARY KEY, "
                + Enemy.COLUMN_NAME + " TEXT, "
                + Enemy.COLUMN_HP + " INTEGER, "
                + Enemy.COLUMN_DMG + " INTEGER"
                + ");";

        db.execSQL(weaponSQL);
        db.execSQL(skillSQL);
        db.execSQL(armorSQL);
        db.execSQL(potionSQL);
        db.execSQL(townSQL);
        db.execSQL(enemySQL);

        //populates the DB with objects
        ObjectCreation oc = new ObjectCreation(this);
        oc.populateDb(db);
        Log.i("DATABASE", "SUCCESSFULLY ADDED ALL ROWS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String weaponSQL, armorSQL, potionSQL, skillSQL, townSQL;

        weaponSQL = "DROP TABLE IF EXIST " + Weapon.TABLE_NAME + ";";
        skillSQL = "DROP TABLE IF EXIST " + Skill.TABLE_NAME + ";";
        armorSQL = "DROP TABLE IF EXIST " + Armor.TABLE_NAME + ";";
        potionSQL = "DROP TABLE IF EXIST " + Potion.TABLE_NAME + ";";
        townSQL = "DROP TABLE IF EXIST " + Town.TABLE_NAME + ";";

        db.execSQL(weaponSQL);
        db.execSQL(skillSQL);
        db.execSQL(armorSQL);
        db.execSQL(potionSQL);
        db.execSQL(townSQL);

        onCreate(db);
    }

    public void addWeapon (SQLiteDatabase db, Weapon weapon, long skill_1, long skill_2, long skill_3, long skill_4) {
        ContentValues cv = new ContentValues();
        cv.put(Weapon.COLUMN_ID, weapon.getId());
        cv.put(Weapon.COLUMN_NAME, weapon.getName());
        cv.put(Weapon.COLUMN_DESCRIPTION, weapon.getDescription());
        cv.put(Weapon.COLUMN_STAT, weapon.getStat().toString());
        cv.put(Weapon.COLUMN_PRICE, weapon.getPrice());
        cv.put(Weapon.COLUMN_SKILL_ID_1, skill_1);
        cv.put(Weapon.COLUMN_SKILL_ID_2, skill_2);
        cv.put(Weapon.COLUMN_SKILL_ID_3, skill_3);
        cv.put(Weapon.COLUMN_SKILL_ID_4, skill_4);

        db.insert(Weapon.TABLE_NAME, null, cv);
    }

    public boolean addWeapon (Weapon weapon, long weaponId, long skill_1, long skill_2, long skill_3, long skill_4) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Weapon.COLUMN_ID, weaponId);
        cv.put(Weapon.COLUMN_NAME, weapon.getName());
        cv.put(Weapon.COLUMN_DESCRIPTION, weapon.getDescription());
        cv.put(Weapon.COLUMN_STAT, weapon.getStat().toString());
        cv.put(Weapon.COLUMN_PRICE, weapon.getPrice());
        cv.put(Weapon.COLUMN_SKILL_ID_1, skill_1);
        cv.put(Weapon.COLUMN_SKILL_ID_2, skill_2);
        cv.put(Weapon.COLUMN_SKILL_ID_3, skill_3);
        cv.put(Weapon.COLUMN_SKILL_ID_4, skill_4);

        long id = db.insert(Weapon.TABLE_NAME, null, cv);
        db.close();
        return (id != 1);
    }

    public boolean updateWeapon (Weapon weapon, long id, long skill_1, long skill_2, long skill_3, long skill_4) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Weapon.COLUMN_NAME, weapon.getName());
        cv.put(Weapon.COLUMN_DESCRIPTION, weapon.getDescription());
        cv.put(Weapon.COLUMN_STAT, weapon.getStat().toString());
        cv.put(Weapon.COLUMN_PRICE, weapon.getPrice());
        cv.put(Weapon.COLUMN_SKILL_ID_1, skill_1);
        cv.put(Weapon.COLUMN_SKILL_ID_2, skill_2);
        cv.put(Weapon.COLUMN_SKILL_ID_3, skill_3);
        cv.put(Weapon.COLUMN_SKILL_ID_4, skill_4);

        int rows = db.update(Weapon.TABLE_NAME, cv, Weapon.COLUMN_ID + " =?", new String[]{id + ""});
        db.close();
        return (rows > 0);
    }

    public Weapon getWeapon (long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Weapon.TABLE_NAME,
                null,
                Weapon.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Weapon w = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Weapon.COLUMN_NAME));
            String description = c.getString(c.getColumnIndex(Weapon.COLUMN_DESCRIPTION));
            Stat stat = Stat.valueOf(c.getString(c.getColumnIndex(Weapon.COLUMN_STAT)));
            int price =  c.getInt(c.getColumnIndex(Weapon.COLUMN_PRICE));
            int skill_1 = c.getInt(c.getColumnIndex(Weapon.COLUMN_SKILL_ID_1));
            int skill_2 = c.getInt(c.getColumnIndex(Weapon.COLUMN_SKILL_ID_2));
            int skill_3 = c.getInt(c.getColumnIndex(Weapon.COLUMN_SKILL_ID_3));
            int skill_4 = c.getInt(c.getColumnIndex(Weapon.COLUMN_SKILL_ID_4));

            w = new Weapon((int)id, name, description, stat, price);
            w.addSkill(skill_1, getSkill(skill_1));
            w.addSkill(skill_2, getSkill(skill_2));
            w.addSkill(skill_3, getSkill(skill_3));
            w.addSkill(skill_4, getSkill(skill_4));
        }

        c.close();
        db.close();
        return w;
    }

    public boolean deleteWeapon (long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Weapon.TABLE_NAME, Weapon.COLUMN_ID + " =?", new String[]{id + ""});
        return rows > 0;
    }

    public void addSkill (SQLiteDatabase db, Skill skill) {
        ContentValues cv = new ContentValues();
        cv.put(Skill.COLUMN_ID, skill.getId());
        cv.put(Skill.COLUMN_NAME, skill.getName());
        cv.put(Skill.COLUMN_DAMAGE, skill.getDamage());
        cv.put(Skill.COLUMN_MANA_COST, skill.getManaCost());
        db.insert(Skill.TABLE_NAME, null, cv);
    }

    public boolean addSkill (Skill skill, long skillId) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Skill.COLUMN_ID, skillId);
        cv.put(Skill.COLUMN_NAME, skill.getName());
        cv.put(Skill.COLUMN_DAMAGE, skill.getDamage());
        cv.put(Skill.COLUMN_MANA_COST, skill.getManaCost());

        long id = db.insert(Skill.TABLE_NAME, null, cv);
        db.close();
        return (id != 1);
    }

    public boolean updateSkill (Skill skill, long id) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Skill.COLUMN_NAME, skill.getName());
        cv.put(Skill.COLUMN_DAMAGE, skill.getDamage());
        cv.put(Skill.COLUMN_MANA_COST, skill.getManaCost());

        int rows = db.update(Skill.TABLE_NAME, cv, Skill.COLUMN_ID + " =?", new String[]{id + ""});
        db.close();
        return (rows > 0);
    }

    public Skill getSkill (long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Skill.TABLE_NAME,
                null,
                Skill.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Skill s = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Skill.COLUMN_NAME));
            int damage = c.getInt(c.getColumnIndex(Skill.COLUMN_DAMAGE));
            int manaCost = c.getInt(c.getColumnIndex(Skill.COLUMN_MANA_COST));

            s = new Skill((int)id, name, damage, manaCost);
        }

        c.close();
        db.close();
        return s;
    }

    public boolean deleteSkill (long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Skill.TABLE_NAME, Skill.COLUMN_ID + " =?", new String[]{id + ""});
        return rows > 0;
    }

    public void addArmor (SQLiteDatabase db, Armor armor) {
        ContentValues cv = new ContentValues();
        cv.put(Armor.COLUMN_ID, armor.getId());
        cv.put(Armor.COLUMN_NAME, armor.getName());
        cv.put(Armor.COLUMN_DESCRIPTION, armor.getDescription());
        cv.put(Armor.COLUMN_STAT, armor.getStat().toString());
        cv.put(Armor.COLUMN_PRICE, armor.getPrice());
        cv.put(Armor.COLUMN_HP, armor.getHP());
        cv.put(Armor.COLUMN_MP, armor.getMP());
        cv.put(Armor.COLUMN_STATVAL, armor.getStatValue());
        db.insert(Armor.TABLE_NAME, null, cv);
    }

    public boolean addArmor (Armor armor, long armorId) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Armor.COLUMN_ID, armorId);
        cv.put(Armor.COLUMN_NAME, armor.getName());
        cv.put(Armor.COLUMN_DESCRIPTION, armor.getDescription());
        cv.put(Armor.COLUMN_STAT, armor.getStat().toString());
        cv.put(Armor.COLUMN_PRICE, armor.getPrice());
        cv.put(Armor.COLUMN_HP, armor.getHP());
        cv.put(Armor.COLUMN_MP, armor.getMP());
        cv.put(Armor.COLUMN_STATVAL, armor.getStatValue());

        long id = db.insert(Armor.TABLE_NAME, null, cv);
        db.close();
        return (id != 1);
    }

    public boolean updateArmor (Armor armor, long id) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Armor.COLUMN_NAME, armor.getName());
        cv.put(Armor.COLUMN_DESCRIPTION, armor.getDescription());
        cv.put(Armor.COLUMN_STAT, armor.getStat().toString());
        cv.put(Armor.COLUMN_PRICE, armor.getPrice());
        cv.put(Armor.COLUMN_HP, armor.getHP());
        cv.put(Armor.COLUMN_MP, armor.getMP());
        cv.put(Armor.COLUMN_STATVAL, armor.getStatValue());

        int rows = db.update(Armor.TABLE_NAME, cv, Armor.COLUMN_ID + " =?", new String[]{id + ""});
        db.close();
        return (rows > 0);
    }

    public Armor getArmor (long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Armor.TABLE_NAME,
                null,
                Armor.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Armor a = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Armor.COLUMN_NAME));
            String description = c.getString(c.getColumnIndex(Armor.COLUMN_DESCRIPTION));
            Stat stat = Stat.valueOf(c.getString(c.getColumnIndex(Armor.COLUMN_STAT)));
            int price =  c.getInt(c.getColumnIndex(Armor.COLUMN_PRICE));
            int hp = c.getInt(c.getColumnIndex(Armor.COLUMN_HP));
            int mp = c.getInt(c.getColumnIndex(Armor.COLUMN_MP));
            int statval = c.getInt(c.getColumnIndex(Armor.COLUMN_STATVAL));

            a = new Armor((int)id, name, description, stat, statval, price, hp ,mp);
        }

        c.close();
        db.close();
        return a;
    }

    public boolean deleteArmor (long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Armor.TABLE_NAME, Armor.COLUMN_ID + " =?", new String[]{id + ""});
        return rows > 0;
    }

    public void addPotion (SQLiteDatabase db, Potion potion) {
        ContentValues cv = new ContentValues();
        cv.put(Potion.COLUMN_ID, potion.getId());
        cv.put(Potion.COLUMN_NAME, potion.getName());
        cv.put(Potion.COLUMN_DESCRIPTION, potion.getDescription());
        cv.put(Potion.COLUMN_STAT, potion.getStat().toString());
        cv.put(Potion.COLUMN_PRICE, potion.getPrice());
        cv.put(Potion.COLUMN_POTIONTYPE, potion.getPotionType().toString());
        cv.put(Potion.COLUMN_AMOUNT, potion.getAmount());
        cv.put(Potion.COLUMN_TIME, potion.getTime());
        db.insert(Potion.TABLE_NAME, null, cv);
    }

    public boolean addPotion (Potion potion, long potionId) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Potion.COLUMN_ID, potionId);
        cv.put(Potion.COLUMN_NAME, potion.getName());
        cv.put(Potion.COLUMN_DESCRIPTION, potion.getDescription());
        cv.put(Potion.COLUMN_STAT, potion.getStat().toString());
        cv.put(Potion.COLUMN_PRICE, potion.getPrice());
        cv.put(Potion.COLUMN_POTIONTYPE, potion.getPotionType().toString());
        cv.put(Potion.COLUMN_AMOUNT, potion.getAmount());
        cv.put(Potion.COLUMN_TIME, potion.getTime());

        long id = db.insert(Potion.TABLE_NAME, null, cv);
        db.close();
        return (id != 1);
    }

    public boolean updatePotion (Potion potion, long id) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Potion.COLUMN_NAME, potion.getName());
        cv.put(Potion.COLUMN_DESCRIPTION, potion.getDescription());
        cv.put(Potion.COLUMN_STAT, potion.getStat().toString());
        cv.put(Potion.COLUMN_PRICE, potion.getPrice());
        cv.put(Potion.COLUMN_POTIONTYPE, potion.getPotionType().toString());
        cv.put(Potion.COLUMN_AMOUNT, potion.getAmount());
        cv.put(Potion.COLUMN_TIME, potion.getTime());

        int rows = db.update(Potion.TABLE_NAME, cv, Potion.COLUMN_ID + " =?", new String[]{id + ""});
        db.close();
        return (rows > 0);
    }

    public Potion getPotion (long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Potion.TABLE_NAME,
                null,
                Potion.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Potion p = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Potion.COLUMN_NAME));
            String description = c.getString(c.getColumnIndex(Potion.COLUMN_DESCRIPTION));
            Stat stat = Stat.valueOf(c.getString(c.getColumnIndex(Potion.COLUMN_STAT)));
            int price =  c.getInt(c.getColumnIndex(Potion.COLUMN_PRICE));
            PotionType potionType = PotionType.valueOf(c.getString(c.getColumnIndex(Potion.COLUMN_POTIONTYPE)));
            int amount = c.getInt(c.getColumnIndex(Potion.COLUMN_AMOUNT));
            int time = c.getInt(c.getColumnIndex(Potion.COLUMN_TIME));

            p = new Potion((int)id, name, description, stat, price, amount, time, potionType);
        }

        c.close();
        db.close();
        return p;
    }

    public boolean deletePotion (long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Potion.TABLE_NAME, Potion.COLUMN_ID + " =?", new String[]{id + ""});
        return rows > 0;
    }

    public void addTown (SQLiteDatabase db, Town town) {
        ContentValues cv = new ContentValues();
        cv.put(Town.COLUMN_ID, town.getId());
        cv.put(Town.COLUMN_NAME, town.getName());
        cv.put(Town.COLUMN_DESCRIPTION, town.getDescription());
        cv.put(Town.COLUMN_LOCATION, town.getLocation());
        db.insert(Town.TABLE_NAME, null, cv);
    }

    public boolean addTown (Town town, long townId) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Town.COLUMN_ID, townId);
        cv.put(Town.COLUMN_NAME, town.getName());
        cv.put(Town.COLUMN_DESCRIPTION, town.getDescription());
        cv.put(Town.COLUMN_LOCATION, town.getLocation());

        long id = db.insert(Town.TABLE_NAME, null, cv);
        db.close();
        return (id != 1);
    }

    public boolean updateTown (Town town, long id) {
        SQLiteDatabase db = getWritableDatabase ();

        ContentValues cv = new ContentValues();
        cv.put(Town.COLUMN_NAME, town.getName());
        cv.put(Town.COLUMN_DESCRIPTION, town.getDescription());
        cv.put(Town.COLUMN_LOCATION, town.getLocation());

        int rows = db.update(Town.TABLE_NAME, cv, Town.COLUMN_ID + " =?", new String[]{id + ""});
        db.close();
        return (rows > 0);
    }

    public Town getTown (long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Town.TABLE_NAME,
                null,
                Town.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Town t = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Town.COLUMN_NAME));
            String description = c.getString(c.getColumnIndex(Town.COLUMN_DESCRIPTION));
            float location = c.getFloat(c.getColumnIndex(Town.COLUMN_LOCATION));

            t = new Town((int)id, name, description, location);
        }

        c.close();
        db.close();
        return t;
    }

    public boolean deleteTown (long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(Town.TABLE_NAME, Town.COLUMN_ID + " =?", new String[]{id + ""});
        return rows > 0;
    }

    public void addEnemy(SQLiteDatabase db, Enemy enemy){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Enemy.COLUMN_ID, enemy.getId());
        contentValues.put(Enemy.COLUMN_NAME, enemy.getName());
        contentValues.put(Enemy.COLUMN_HP, enemy.getHp());
        contentValues.put(Enemy.COLUMN_DMG, enemy.getDmg());

        db.insert(Enemy.TABLE_NAME, null, contentValues);
    }

    public Enemy getEnemy(long id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Enemy.TABLE_NAME,
                null,
                Enemy.COLUMN_ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        Enemy e = null;

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex(Enemy.COLUMN_NAME));
            int hp = c.getInt(c.getColumnIndex(Enemy.COLUMN_HP));
            int dmg = c.getInt(c.getColumnIndex(Enemy.COLUMN_DMG));

            e = new Enemy((int)id, name, hp, dmg);
        }

        c.close();
        db.close();
        return e;
    }
}
