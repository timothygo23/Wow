package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

import com.agorda.wow.R;
import com.agorda.wow.ui.MainView;

/**
 * Created by Timothy on 13/12/2017.
 */

public class ScrollingBackground {
    private Context context;
    private Background background1, background2;
    private float x, x2, y;

    private boolean enabled;
    private Paint paint;

    public ScrollingBackground(Context context, float y){
        this.context = context;
        x = 0;
        x2 = 0 + MainView.WIDTH;
        this.y = y;
        enabled = false;

        background1 = new Background(context);
        background2 = new Background(context);
        background1.setBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.background1));
        background1.setSize(MainView.WIDTH * MainView.SCALE_WIDTH, MainView.HEIGHT/ 2.8f * MainView.SCALE_HEIGHT);
        background1.set_Y(y);
        background2.setBackground(BitmapFactory.decodeResource(context.getResources(), R.drawable.background2));
        background2.setSize(MainView.WIDTH * MainView.SCALE_WIDTH, MainView.HEIGHT / 2.8f * MainView.SCALE_HEIGHT);
        background2.set_Y(y);

        initPaint();
    }

    public void initPaint(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(100);
    }

    public void draw(Canvas canvas){
        background1.set_X(x).draw(canvas);
        background2.set_X(x2).draw(canvas);

        /*if(!enabled) {
            canvas.drawRect(0, y, MainView.WIDTH  * MainView.SCALE_WIDTH, y+MainView.HEIGHT/2.8f  * MainView.SCALE_HEIGHT, paint);
        }*/
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public void update(){
        if(enabled){
            x -= 3;
            x2 -= 3;
            if(x < 0 - MainView.WIDTH * MainView.SCALE_WIDTH + 2.5f)
                x = MainView.WIDTH * MainView.SCALE_WIDTH;
            if(x2 < 0 - MainView.WIDTH * MainView.SCALE_WIDTH + 2.5f)
                x2 = MainView.WIDTH * MainView.SCALE_WIDTH;
        }
    }

    public float getBottom(){
        return background1.getThisBottom();
    }
}
