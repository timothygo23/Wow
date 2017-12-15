package com.agorda.wow.ui.ui_element.player_status;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.agorda.wow.R;
import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.equipment.Armor;
import com.agorda.wow.gameElements.equipment.Equipment;
import com.agorda.wow.gameElements.equipment.Potion;
import com.agorda.wow.gameElements.equipment.Weapon;
import com.agorda.wow.ui.scale.ScaleBitmap;

/**
 * Created by Timothy on 16/12/2017.
 */

public class PlayerStatusIcon extends View {
    private Context context;
    private Equipment equipment;
    private Bitmap icon;
    private float x, y;

    public PlayerStatusIcon(Context context, Equipment equipment, float x, float y) {
        super(context);
        this.context = context;
        this.x = x;
        this.y = y;
        this.equipment = equipment;
        initIcon();
    }

    public void initIcon(){
        if(equipment != null) {
            if (equipment instanceof Weapon) {
                switch (equipment.getId()) {
                    case ObjectId.DAGGER:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.dagger);
                        break;
                    case ObjectId.HAMMER:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.hammer);
                        break;
                }
            } else if (equipment instanceof Armor) {
                switch (equipment.getId()) {
                    case ObjectId.HAT:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.hat);
                        break;
                    case ObjectId.STEEL_ARMOUR:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.steel_armor);
                        break;
                }
            } else if (equipment instanceof Potion) {
                switch (equipment.getId()) {
                    case ObjectId.HP_REGEN:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.health_potion);
                        break;
                    case ObjectId.MP_REGEN:
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.mana_potion);
                        break;
                }
            }
        }else{
            icon = BitmapFactory.decodeResource(getResources(), R.drawable.health_potion);
        }
        icon = ScaleBitmap.scaleBitmap(icon, icon.getWidth() * 0.6f, icon.getHeight() * 0.6f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(icon, x - icon.getWidth() / 2, y, null);
    }
}
