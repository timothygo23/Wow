package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import com.agorda.wow.R;

/**
 * Created by Timothy on 12/12/2017.
 */

public class CustomButton extends View {

    private Bitmap button;
    private int x, y, width, height;

    public CustomButton(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

    public CustomButton setButton(Bitmap button){
        this.button = button;
        return this;
    }

    public CustomButton setWidth(int width){
        this.width = width;
        return this;
    }

    public CustomButton setHeight(int height){
        this.height = height;
        return this;
    }

    public CustomButton setX(int x){
        this.x = x;
        return this;
    }

    public CustomButton setY(int y){
        this.y = y;
        return this;
    }

}
