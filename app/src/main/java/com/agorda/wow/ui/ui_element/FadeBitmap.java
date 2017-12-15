package com.agorda.wow.ui.ui_element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.agorda.wow.ui.GameScreenManager;
import com.agorda.wow.ui.screen.PrologueScreen;
import com.agorda.wow.ui.screen.Screen;
import com.agorda.wow.ui.screen.ScreenListener;

/**
 * Created by Timothy on 13/12/2017.
 */

public class FadeBitmap extends View {
    public static final int FADE_IN = 0;
    public static final int FADE_OUT = 1;

    private Screen nextScreen;
    private Context context;

    private Bitmap bitmap;
    private Paint bitmapPaint;
    private AlphaAnimation bitmapAnimation;
    private Transformation bitmapTransformation;

    public FadeBitmap(Context context){
        super(context);
    }

    public FadeBitmap(Context context, Screen next) {
        super(context);
        this.context = context;
        this.nextScreen = next;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void setFade(int fade, final ScreenListener screenListener){
        //for alpha value of bitmap
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //to house the current value of the animation
        bitmapTransformation = new Transformation();

        if(fade == FADE_IN){
            bitmapAnimation = new AlphaAnimation(0f, 1f);
        }else{
            bitmapAnimation = new AlphaAnimation(1f, 0f);
        }

        //animation is basically for timing math
        bitmapAnimation.setDuration(600);
        bitmapAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Thread(){
                    public void run(){
                        try{
                            sleep(1000);
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        screenListener.switchScreen();
                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bitmapAnimation.start();
        bitmapAnimation.getTransformation(System.currentTimeMillis(), bitmapTransformation);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, canvas.getWidth() / 2 - bitmap.getWidth()/ 2, canvas.getHeight() / 2 - bitmap.getHeight() / 2, bitmapPaint);
        if(bitmapAnimation.hasStarted() && !bitmapAnimation.hasEnded()){
            bitmapAnimation.getTransformation(System.currentTimeMillis(), bitmapTransformation);
            bitmapPaint.setAlpha((int)(255 * bitmapTransformation.getAlpha()));
            invalidate();
        }else{
            bitmapPaint.setAlpha(255);
        }
    }
}
