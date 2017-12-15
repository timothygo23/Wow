package com.agorda.wow.ui.ui_element.player_status;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Timothy on 15/12/2017.
 */

public class Bar extends View{
    private float x, y, width, height;
    private int maxValue, currentValue;
    private Paint paint, paintBg;

    public Bar(Context context, int maxValue, int currentValue, float x, float y, float width, float height) {
        super(context);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
    }
    public void setPaint(int color, int color2){
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        paintBg = new Paint();
        paintBg.setColor(color2);
        paintBg.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(x, y, width, y+height, paintBg);
        canvas.drawRect(x, y, width * (currentValue / (float) maxValue), y+height, paint);
    }

    public void setMaxValue(int maxValue){
        this.maxValue = maxValue;
    }

    public void setCurrentValue(int currentValue){
        this.currentValue = currentValue;
    }

    public float getThisBottom(){
        return y + height;
    }
}
