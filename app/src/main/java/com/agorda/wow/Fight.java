package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;
import com.agorda.wow.util.NotificationUtil;

import org.w3c.dom.Text;

public class Fight extends AppCompatActivity {
    private Button button;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Player player;
    private Enemy enemy;

    private TextView fight_enemy, fight_enemy_stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        //back end elements
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        databaseHelper = new DatabaseHelper(this);

        enemy = GameSave.loadEnemy(dsp, databaseHelper);
        player = GameSave.loadPlayer(dsp, databaseHelper);

        init();
        initHandler();

    }

    private void init(){
        button = (Button)findViewById(R.id.fight_button);
        fight_enemy = (TextView)findViewById(R.id.fight_enemy);
        fight_enemy_stats = (TextView)findViewById(R.id.fight_enemy_stats);
        fight_enemy.setText(enemy.getName());
        fight_enemy_stats.setText("HP: " + enemy.getHp() + " DMG: " + enemy.getDmg());
    }

    private void initHandler(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change player state back to walking
                player.setState(PlayerState.WALKING);
                GameSave.save(dsp.edit(), player); //after fight

                Intent adventureIntent = new Intent(getBaseContext(), Adventure.class);
                startActivity(adventureIntent);
                finish();
            }
        });
    }

}
