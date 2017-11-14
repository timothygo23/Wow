package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;
import com.agorda.wow.util.NotificationUtil;
import com.agorda.wow.util.StepCounter;

public class Town extends AppCompatActivity{
    private Button town_button_adventure;
    private Button town_button_weapon,
            town_button_armor,
            town_button_potion,
            town_button_inn;

    private TextView town_tv_name,
            town_tv_level,
            town_tv_xp,
            town_tv_gold,
            town_tv_armor,
            town_tv_weapon,
            town_tv_townname,
            town_tv_hp,
            town_tv_mp;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        //backend elements
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        databaseHelper = new DatabaseHelper(this);

        player = GameSave.loadPlayer(dsp, databaseHelper);

        town_button_adventure = (Button)findViewById(R.id.town_button_adventure);
        town_button_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adventureActivity = new Intent(getBaseContext(), Map.class);
                startActivity(adventureActivity);
            }
        });

        setUp();
    }

    public void setUp(){
        town_button_weapon = (Button)findViewById(R.id.town_button_weapon);
        town_button_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to shop intent;
            }
        });

        town_button_armor = (Button)findViewById(R.id.town_button_armor);
        town_button_armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to shop intent;
            }
        });

        town_button_potion = (Button)findViewById(R.id.town_button_potion);
        town_button_potion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to shop intent;
            }
        });

        town_button_inn = (Button)findViewById(R.id.town_button_inn);
        town_button_inn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //heal player
            }
        });

        //textviews
        town_tv_name = (TextView)findViewById(R.id.town_tv_name);
        town_tv_level = (TextView)findViewById(R.id.town_tv_level);
        town_tv_xp = (TextView)findViewById(R.id.town_tv_xp);
        town_tv_gold = (TextView)findViewById(R.id.town_tv_gold);
        town_tv_weapon = (TextView)findViewById(R.id.town_tv_weapon);
        town_tv_armor = (TextView)findViewById(R.id.town_tv_armor);
        town_tv_townname = (TextView)findViewById(R.id.town_tv_townname);
        town_tv_hp = (TextView)findViewById(R.id.town_tv_hp);
        town_tv_mp = (TextView)findViewById(R.id.town_tv_mp);

        town_tv_name.setText(player.getData().getName());
        town_tv_level.setText("Level: " + player.getData().getLevel());
        town_tv_xp.setText("Exp: " + player.getData().getXP());
        town_tv_gold.setText("Gold: " + player.getData().getGold());
        town_tv_weapon.setText(player.getData().getWeapon().getName());
        town_tv_armor.setText(player.getData().getArmor().getName());
        town_tv_townname.setText(player.getCurrentTown().getName());
        town_tv_hp.setText("HP: " + player.getData().getHP() + "/" + player.getData().getMaxHP());
        town_tv_mp.setText("MP: " + player.getData().getMP() + "/" + player.getData().getMaxMP());
    }

}
