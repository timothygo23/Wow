package com.agorda.wow.gameElements.equipment;

import com.agorda.wow.gameElements.types.Stat;

/**
 * Created by Timothy on 07/11/2017.
 */

public class Weapon extends Equipment{
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
