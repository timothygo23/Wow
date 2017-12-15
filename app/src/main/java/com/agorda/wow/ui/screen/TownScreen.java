package com.agorda.wow.ui.screen;

import android.content.Context;
import android.graphics.Canvas;

import com.agorda.wow.ui.GameScreenManager;

/**
 * Created by Timothy on 14/12/2017.
 */

public class TownScreen implements Screen {
    private Context context;
    private GameScreenManager gsm;

    public TownScreen(Context context, GameScreenManager gsm) {
        this.context = context;
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void initHandler() {

    }

    @Override
    public void render(Canvas canvas) {

    }

    @Override
    public void update(double deltaTime) {
        gsm.pop(new MapChooseScreen(context,gsm));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
