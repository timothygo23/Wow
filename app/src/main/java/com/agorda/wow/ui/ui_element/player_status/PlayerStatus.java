package com.agorda.wow.ui.ui_element.player_status;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.ui.MainView;

/**
 * Created by Timothy on 14/12/2017.
 */

public class PlayerStatus extends View implements View.OnTouchListener {
    private Context context;

    private Player player;
    private float x, y;
    private Bar hp_bar, mp_bar;

    private PlayerStatusIcon weapon, armour, potion;
    private TextView textView;

    public PlayerStatus(Context context, Player player , float x, float y) {
        super(context);
        this.context = context;
        this.x = x;
        this.y = y;
        this.player = player;

        initBars();
        initIcons();
    }

    public void initBars(){
        int x = 150;
        float width = (MainView.WIDTH - x) * MainView.SCALE_WIDTH;
        float height = 50 * MainView.SCALE_HEIGHT;

        hp_bar = new Bar(getContext(), player.getData().getMaxHP(), player.getData().getHP(),
                x, y + 100 * MainView.SCALE_HEIGHT,
                width, height);
        hp_bar.setPaint(Color.parseColor("#f42020"), Color.parseColor("#e15252"));

        mp_bar = new Bar(getContext(), player.getData().getMaxMP(), player.getData().getMP(),
                x,  hp_bar.getThisBottom() + 45 * MainView.SCALE_HEIGHT,
                width, height);
        mp_bar.setPaint(Color.parseColor("#1115f6"), Color.parseColor("#7273cc"));
    }

    public void initIcons(){
        weapon = new PlayerStatusIcon(getContext(), player.getData().getWeapon(), MainView.WIDTH / 3 / 2, mp_bar.getThisBottom() + 90 * MainView.SCALE_HEIGHT);
        armour = new PlayerStatusIcon(getContext(), player.getData().getArmor(), MainView.WIDTH / 3 * 2 - MainView.WIDTH / 3 / 2, mp_bar.getThisBottom() + 90 * MainView.SCALE_HEIGHT);
        potion = new PlayerStatusIcon(getContext(), player.getData().getPotion(), MainView.WIDTH - MainView.WIDTH / 3 / 2, mp_bar.getThisBottom() + 90 * MainView.SCALE_HEIGHT);
        weapon.setOnTouchListener(this);
        armour.setOnTouchListener(this);
        potion.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        hp_bar.draw(canvas);
        mp_bar.draw(canvas);

        weapon.draw(canvas);
        armour.draw(canvas);
        potion.draw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(context, "TANGINA", Toast.LENGTH_SHORT).show();
        return false;
    }
}
