package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Timothy on 08/12/2017.
 */

public class Background extends View {
    private int width, height, x, y;
    private Bitmap background;

    private Paint paint;

    public Background(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(background == null)
            canvas.drawRect(x, y, width, height, paint);
        else
            canvas.drawBitmap(background, x,y, null);
    }

    public Background setPaint(Paint paint){
        this.paint = paint;
        return this;
    }

    public Background setBackground(Bitmap background){
        this.background = background;
        return this;
    }

    public Background setWidth(int width){
        this.width = width;
        return this;
    }

    public Background setHeight(int height){
        this.height = height;
        return this;
    }

    public Background setX(int x){
        this.x = x;
        return this;
    }

    public Background setY(int y){
        this.y = y;
         return this;
    }

}
