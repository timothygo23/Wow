package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agorda.wow.gameElements.enemy.Enemy;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;
import com.agorda.wow.util.NotificationUtil;

import org.w3c.dom.Text;

public class Fight extends AppCompatActivity {
    public static final String TURN = "fight_turn";
    public static final int PLAYER_TURN = 1;
    public static final int ENEMY_TURN = 2;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Player player;
    private Enemy enemy;
    private int currentTurn;

    private Button[] skills;

    private TextView fight_enemy, fight_enemy_stats;
    private TextView player_hp, player_mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        //back end elements
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        databaseHelper = new DatabaseHelper(this);

        enemy = GameSave.loadEnemy(dsp, databaseHelper);
        player = GameSave.loadPlayer(dsp, databaseHelper);
        currentTurn = GameSave.getTurn(dsp);

        init();
        initHandler();

        if(currentTurn == ENEMY_TURN)
            enemyTurn();
    }

    private void init(){
        fight_enemy = (TextView)findViewById(R.id.fight_enemy);
        fight_enemy_stats = (TextView)findViewById(R.id.fight_enemy_stats);
        fight_enemy.setText(enemy.getName());
        fight_enemy_stats.setText("HP: " + enemy.getHp() + " DMG: " + enemy.getDmg());

        skills = new Button[4];
        skills[0] = (Button)findViewById(R.id.button_skill1);
        skills[1] = (Button)findViewById(R.id.button_skill2);
        skills[2] = (Button)findViewById(R.id.button_skill3);
        skills[3] = (Button)findViewById(R.id.button_skill4);

        player_hp = (TextView)findViewById(R.id.player_hp);
        player_mp = (TextView)findViewById(R.id.player_mp);
        player_hp.setText("HP: " + player.getData().getHP());
        player_mp.setText("MP: " + player.getData().getMP());

        if(currentTurn == ENEMY_TURN){
            for(int i = 0; i < skills.length; i++){
                skills[i].setEnabled(false);
            }
        }

    }

    private void initHandler(){
        for(int i = 0; i < skills.length; i++){
            final int index = i;
            skills[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player.skill(index, enemy)) {
                        fightOver();
                        return;
                    }
                    update();
                    endTurn();
                    Toast.makeText(getBaseContext(), player.getData().getWeapon().getSkills()[index].getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void endTurn(){
        if(currentTurn == PLAYER_TURN)
            currentTurn = ENEMY_TURN;
        else if(currentTurn == ENEMY_TURN)
            currentTurn = PLAYER_TURN;

        if(player.getState() == PlayerState.DEAD){
            // go to town
        }

        GameSave.saveTurn(dsp.edit(), currentTurn);
        GameSave.save(dsp.edit(), player);
        GameSave.saveEnemy(dsp.edit(), enemy);

        //disable/enable buttons
        if(currentTurn == ENEMY_TURN){
            for(int i = 0; i < skills.length; i++){
                skills[i].setEnabled(false);
            }
            enemyTurn();
        }else{
            for(int i = 0; i < skills.length; i++){
                skills[i].setEnabled(true);
            }
        }
    }

    public void enemyTurn(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player.getData().setHP(player.getData().getHP() - enemy.getDmg());
                if(player.getData().getHP() <= 0) {
                    player.setState(PlayerState.DEAD);
                    player.setDestination(null);
                }
                update();
                endTurn();
            }
        }, 1500);
    }

    private void fightOver(){
        //change player state back to walking
        player.setState(PlayerState.WALKING);
        currentTurn = PLAYER_TURN;
        GameSave.saveTurn(dsp.edit(), currentTurn);
        GameSave.save(dsp.edit(), player); //after fight

        Intent adventureIntent = new Intent(getBaseContext(), Adventure.class);
        startActivity(adventureIntent);
        finish();
    }

    private void update(){
        fight_enemy_stats.setText("HP: " + enemy.getHp() + " DMG: " + enemy.getDmg());
        player_hp.setText("HP: " + player.getData().getHP());
        player_mp.setText("MP: " + player.getData().getMP());
    }

}
