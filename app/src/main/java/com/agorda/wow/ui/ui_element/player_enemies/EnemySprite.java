package com.agorda.wow.ui.ui_element.player_enemies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.agorda.wow.R;
import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.ui.scale.ScaleBitmap;

/**
 * Created by Timothy on 14/12/2017.
 */

public class EnemySprite extends View{
    private Bitmap sprite;
    private float x, y, widthScale, heightScale;
    private int yOffset;

    public EnemySprite(Context context, float x, float y, float widthScale, float heightScale) {
        super(context);
        this.x = x;
        this.y = y;
        this.widthScale = widthScale;
        this.heightScale = heightScale;
    }

    public void setEnemySprite(int enemyId){
        switch(enemyId){
            case ObjectId.PYRO:
                sprite = BitmapFactory.decodeResource(getResources(), R.drawable.pyro);
                yOffset = 37;
                break;
            case ObjectId.PLANT:
                sprite = BitmapFactory.decodeResource(getResources(), R.drawable.plant);
                yOffset = 48;
                break;
            case ObjectId.GHOST:
                sprite = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
                yOffset = 35;
                break;
        }

        sprite = ScaleBitmap.scaleBitmap(sprite, sprite.getWidth() * widthScale, sprite.getHeight() * heightScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(sprite, x - sprite.getWidth(), y - sprite.getHeight() + 40 *heightScale, null);
    }


}
