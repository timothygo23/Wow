package com.agorda.wow.ui.ui_element.player_enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import com.agorda.wow.R;
import com.agorda.wow.ui.scale.ScaleBitmap;

/**
 * Created by Timothy on 14/12/2017.
 */

public class PlayerSprite extends View{
    private Bitmap sprite;
    private Animation animation;
    private float x, y, widthScale, heightScale;

    private boolean animate;

    public PlayerSprite(Context context, float x, float y, float widthScale, float heightScale) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.x = x;
        this.y = y;
        this.widthScale = widthScale;
        this.heightScale = heightScale;
        animate = false;
        sprite = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        sprite = ScaleBitmap.scaleBitmap(sprite,
                sprite.getWidth() * widthScale, sprite.getHeight() * heightScale);

        /* init animation here */
        initAnimation();
    }

    public void initAnimation(){
        Bitmap frame_1 = BitmapFactory.decodeResource(getResources(), R.drawable.player_frame1);
        Bitmap frame_2 = BitmapFactory.decodeResource(getResources(), R.drawable.player_frame2);
        Bitmap frame_3 = BitmapFactory.decodeResource(getResources(), R.drawable.player_frame3);
        Bitmap frame_4 = BitmapFactory.decodeResource(getResources(), R.drawable.player_frame4);

        animation = new Animation(100, 4);
        animation.setAnimation(new Bitmap[]{
            ScaleBitmap.scaleBitmap(frame_1, frame_1.getWidth() * widthScale, frame_1.getHeight() * heightScale),
                ScaleBitmap.scaleBitmap(frame_2, frame_2.getWidth() * widthScale, frame_2.getHeight() * heightScale),
                ScaleBitmap.scaleBitmap(frame_3, frame_3.getWidth() * widthScale, frame_3.getHeight() * heightScale),
                ScaleBitmap.scaleBitmap(frame_4, frame_4.getWidth() * widthScale, frame_4.getHeight() * heightScale)
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!animate)
            canvas.drawBitmap(sprite, x, y - sprite.getHeight() + 9 * heightScale, null);
        else
            canvas.drawBitmap(animation.getCurrentFrame(), x, y - sprite.getHeight() + 9 * heightScale, null);
    }

    public void update(double deltaTime){
        if(animate)
            animation.animate(deltaTime);
    }

    public void startAnimation(){
        animate = true;
    }

    public void endAnimation(){
        animate = false;
        animation.reset();
    }
}
