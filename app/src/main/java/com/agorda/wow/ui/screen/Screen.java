package com.agorda.wow.ui.screen;

import android.graphics.Canvas;

/**
 * Created by Timothy on 07/12/2017.
 */

public interface Screen {
    void init();
    void initHandler();
    void render(Canvas canvas);
    void update(double deltaTime);
    void resume();
    void pause();
}
