package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.agorda.wow.R;

/**
 * Created by Timothy on 12/12/2017.
 */

public class Button extends View {

    private Bitmap button;
    private int x, y, width, height;

    public Button(Context context) {
        super(context);
        button = BitmapFactory.decodeResource(getResources(),  R.drawable.button);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(button, x, y, null);

    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    public Button setButton(Bitmap button){
        this.button = button;
        return this;
    }

    public Button setWidth(int width){
        this.width = width;
        return this;
    }

    public Button setHeight(int height){
        this.height = height;
        return this;
    }

    public Button setX(int x){
        this.x = x;
        return this;
    }

    public Button setY(int y){
        this.y = y;
        return this;
    }

}
