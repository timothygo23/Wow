package com.agorda.wow.ui.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.View;

import com.agorda.wow.R;
import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.ui.ui_element.Background;
import com.agorda.wow.ui.ui_element.Button;
import com.agorda.wow.util.DatabaseHelper;

import java.util.logging.Handler;

/**
 * Created by Timothy on 08/12/2017.
 */

public class SplashScreen implements Screen {
    private GameScreenManager gsm;
    private Context context;

    private Background bg;
    private Bitmap logo;
    private Paint backgroundPaint;

    private SharedPreferences dsp;

    private Button button;

    public SplashScreen(Context context, GameScreenManager gsm){
        this.context = context;
        this.gsm = gsm;

        init();
    }

    @Override
    public void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#383838"));
        backgroundPaint.setStyle(Paint.Style.FILL);

        bg = new Background(context);
        bg.setX(0).setY(0).setPaint(backgroundPaint);

        logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);

        //sets up the db (creates it when it doesn't exists yet)
        DatabaseHelper dbh = new DatabaseHelper(context);
        dbh.getWritableDatabase();

        dsp = PreferenceManager.getDefaultSharedPreferences(context);

        button = new Button(context);
        button.setX(0).setY(0);
    }

    @Override
    public void initHandler() {

    }

    @Override
    public void render(Canvas canvas) {
        bg.setWidth(canvas.getWidth()).setHeight(canvas.getHeight()).draw(canvas);
        canvas.drawBitmap(logo, canvas.getWidth() / 2 - logo.getWidth()/ 2, canvas.getHeight() / 2 - logo.getHeight() / 2, null);
        button.draw(canvas);
    }

    @Override
    public void update(double deltaTime) {
       /* if(switchScreen){
            gsm.pop(new PrologueScreen(context, gsm));
        }*/
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
