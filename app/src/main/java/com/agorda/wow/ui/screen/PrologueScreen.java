package com.agorda.wow.ui.screen;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.agorda.wow.ui.GameScreenManager;

/**
 * Created by Timothy on 08/12/2017.
 */

public class PrologueScreen implements Screen {
    private GameScreenManager gsm;
    private Context context;

    public PrologueScreen(Context context, GameScreenManager gsm){
        this.context = context;
        this.gsm = gsm;
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
        System.out.println("prologue screen");
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
