package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;

public class Setup extends AppCompatActivity {
    private Button setup_button_create;
    private EditText setup_et_name;

    private SharedPreferences dsp;
    private SharedPreferences.Editor dspEditor;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //backend elements
        dsp = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());
        dspEditor = dsp.edit ();
        databaseHelper = new DatabaseHelper(this);

        setup_et_name = (EditText) findViewById (R.id.setup_et_name);

        setup_button_create = (Button)findViewById(R.id.setup_button_create);
        setup_button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent for town activity
                //create new player -> shared pref

                Player player = setUpNewPlayer();
                //saves the new player's info
                GameSave.save(dspEditor, player);

                Intent townActivity = new Intent(getBaseContext(), Town.class);
                startActivity(townActivity);
                finish();
            }
        });
    }

    public Player setUpNewPlayer(){
        Player player = new Player (setup_et_name.getText().toString(), databaseHelper.getTown(ObjectId.START_TOWN), PlayerState.TOWN);

        //beginner items here
        player.equip(databaseHelper.getWeapon(ObjectId.DAGGER));
        player.equip(databaseHelper.getArmor(ObjectId.HAT));

        //give potions to new user
        int freePotions = 3;
        for(int i = 0; i < freePotions; i++){
            player.getItems().add(databaseHelper.getPotion(ObjectId.HP_REGEN));
            player.getItems().add(databaseHelper.getPotion(ObjectId.MP_REGEN));
        }

        return player;
    }
}
