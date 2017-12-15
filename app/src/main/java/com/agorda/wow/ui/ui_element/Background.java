package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Timothy on 08/12/2017.
 */

public class Background extends View {
    private float width, height, x, y;
    private Bitmap background;

    private Paint paint;

    public Background(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

    public Background setWidth(float width){
        this.width = width;
        return this;
    }

    public Background setHeight(float height){
        this.height = height;
        return this;
    }

    public Background set_X(float x){
        this.x = x;
        return this;
    }

    public Background set_Y(float y){
        this.y = y;
         return this;
    }

    public void setSize(float width, float height){
        int bmWidth = background.getWidth();
        int bmHeight = background.getHeight();

        float scaleWidth = width / bmWidth;
        float scaleHeight = height / bmHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap newBitmap = Bitmap.createBitmap(background, 0, 0, bmWidth, bmHeight, matrix, false);
        background = newBitmap;
    }

    public float getThisBottom(){
        return y + background.getHeight();
    }

}
