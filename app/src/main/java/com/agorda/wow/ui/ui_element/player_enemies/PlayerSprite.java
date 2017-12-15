package com.agorda.wow.ui.ui_element.player_enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.agorda.wow.R;
import com.agorda.wow.ui.scale.ScaleBitmap;

/**
 * Created by Timothy on 14/12/2017.
 */

public class PlayerSprite extends View{
    private Bitmap sprite;
    private float x, y, widthScale, heightScale;

    public PlayerSprite(Context context, float x, float y, float widthScale, float heightScale) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.x = x;
        this.y = y;
        this.widthScale = widthScale;
        this.heightScale = heightScale;
        sprite = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        sprite = ScaleBitmap.scaleBitmap(sprite,
                sprite.getWidth() * widthScale, sprite.getHeight() * heightScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, x, y - sprite.getHeight() + 9 * heightScale, null);
    }

}
