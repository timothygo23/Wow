package com.agorda.wow.ui.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.preference.PreferenceManager;

import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;

/**
 * Created by Timothy on 14/12/2017.
 */

public class MapChooseScreen implements Screen {
    private Context context;
    private GameScreenManager gsm;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Player player;

    public MapChooseScreen(Context context, GameScreenManager gsm) {
        this.context = context;
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        initBackend();
        /*
            just a random destination at the moment
        */
        com.agorda.wow.gameElements.town.Town destinationTown = databaseHelper.getTown(ObjectId.SECOND_TOWN);
        Destination destination = new Destination(player.getCurrentTown(), destinationTown);

        player.setDestination(destination);
        player.setState(PlayerState.CAMPING);

        GameSave.save(dsp.edit(), player);
    }

    public void initBackend(){
        dsp = PreferenceManager.getDefaultSharedPreferences(context);
        databaseHelper = new DatabaseHelper(context);

        player = GameSave.loadPlayer(dsp, databaseHelper);
    }

    @Override
    public void initHandler() {

    }

    @Override
    public void initViewElements() {

    }

    @Override
    public void render(Canvas canvas) {

    }

    @Override
    public void update(double deltaTime) {
        gsm.pop(new AdventureScreen(context, gsm), null);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
