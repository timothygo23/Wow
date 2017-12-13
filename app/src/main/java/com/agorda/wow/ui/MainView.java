package com.agorda.wow.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.agorda.wow.ui.screen.SplashScreen;

/**
 * Created by Timothy on 07/12/2017.
 */

public class MainView extends SurfaceView implements Runnable {
    private Canvas canvas;
    private GameScreenManager gsm;

    private Thread thread;
    private SurfaceHolder surfaceHolder;

    private boolean drawNow = false;

    public MainView(Context context) {
        super(context);

        surfaceHolder = getHolder();

        gsm = new GameScreenManager();
        gsm.push(new SplashScreen(context, gsm));
    }

    @Override
    public void run() {
        /*
            game loop reference: https://www.youtube.com/watch?v=TNVHWROwYuM
         */
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 1000000000 / fps;
        double deltaTime = 0;

        while(drawNow){

            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }

            long now = System.nanoTime();
            deltaTime += (now - lastTime) / ns;
            lastTime = now;
            if(deltaTime >= 1){
                gsm.getTop().update(deltaTime);
                deltaTime--;
            }

            canvas = surfaceHolder.lockCanvas();
            gsm.getTop().render(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume(){
        drawNow = true;
        thread = new Thread(this);
        thread.start();
        gsm.getTop().resume();
    }

    public void pause(){
        drawNow = false;

        while(true){
            try{
                thread.join();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        thread = null;

        gsm.getTop().pause();
    }

}