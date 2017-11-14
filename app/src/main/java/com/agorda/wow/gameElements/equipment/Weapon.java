package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Weapon extends Equipment{

    public static final String TABLE_NAME = "armor";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STAT = "stat";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SKILL_ID = "skill_id";

    private Skill[] skills;

    public Weapon(String name, String description, Stat stat, int price) {
        super(name, description, stat, price);
        skills = new Skill[4];
    }
    public void updateSkillsDamage(Armor armor){
        if(this.getStat() == armor.getStat()) {
            for(int i = 0; i < skills.length; i++){
                skills[i].updateDamage(armor.getStatValue());
            }
        }
    }

    public void resetSkillBonus() {
        for(int i = 0; i < skills.length; i++){
            skills[i].resetBonus();
        }
    }

    public void setSkills(Skill[] skills){
        this.skills = skills;
    }

    public Skill[] getSkills(){
        return skills;
    }

}
