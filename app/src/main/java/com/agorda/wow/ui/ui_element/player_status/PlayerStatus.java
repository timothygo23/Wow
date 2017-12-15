package com.agorda.wow.ui.ui_element.player_status;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.agorda.wow.ui.MainView;

/**
 * Created by Timothy on 14/12/2017.
 */

public class PlayerStatus extends View {

    private float x, y;
    private Paint paint;

    public PlayerStatus(Context context, float x, float y) {
        super(context);
        this.x = x;
        this.y = y;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(x, y, MainView.WIDTH, MainView.HEIGHT, paint);
    }
}
