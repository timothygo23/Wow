package com.agorda.wow.ui.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.preference.PreferenceManager;

import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;

/**
 * Created by Timothy on 08/12/2017.
 */

public class PrologueScreen implements Screen {
    private GameScreenManager gsm;
    private Context context;

    //back end
    private SharedPreferences dsp;
    private SharedPreferences.Editor dspEditor;

    private DatabaseHelper databaseHelper;

    public PrologueScreen(Context context, GameScreenManager gsm){
        this.context = context;
        this.gsm = gsm;

        init();
    }

    @Override
    public void init() {
        initBackend();
        /*
            create player
         */
        Player player = setUpNewPlayer("tim");
        //saves the new player's info
        GameSave.save(dspEditor, player);
    }

    public void initBackend(){
        //backend elements
        dsp = PreferenceManager.getDefaultSharedPreferences (context);
        dspEditor = dsp.edit ();
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void initHandler() {

    }

    @Override
    public void render(Canvas canvas) {
    }

    @Override
    public void update(double deltaTime) {
        //go to town screen
        gsm.pop(new TownScreen(context, gsm));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public Player setUpNewPlayer(String name){
        Player player = new Player (name, databaseHelper.getTown(ObjectId.START_TOWN), PlayerState.TOWN);

        //beginner items here
        player.equip(databaseHelper.getWeapon(ObjectId.DAGGER));
        player.equip(databaseHelper.getArmor(ObjectId.HAT));

        //give potions to new user
        int freePotions = 3;
        for(int i = 0; i < freePotions; i++){
            player.getItems().add(databaseHelper.getPotion(ObjectId.HP_REGEN));
            player.getItems().add(databaseHelper.getPotion(ObjectId.MP_REGEN));
        }

        return player;
    }
}
