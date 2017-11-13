package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;

public class Setup extends AppCompatActivity {
    private Button setup_button_create;
    private EditText setup_et_name;

    private SharedPreferences dsp;
    private SharedPreferences.Editor dspEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        dsp = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());
        dspEditor = dsp.edit ();

        dspEditor.putBoolean ("is_new_player", true);
        dspEditor.apply ();

        setup_et_name = (EditText) findViewById (R.id.setup_et_name);

        setup_button_create = (Button)findViewById(R.id.setup_button_create);
        setup_button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent for town activity
                //create new player -> shared pref

                Player player = new Player (setup_et_name.getText().toString(), new com.agorda.wow.gameElements.town.Town ("town1", "", 0));
                player.setDestination(new Destination(player.getCurrentTown(), new com.agorda.wow.gameElements.town.Town("town2","This new town is nice.",12)));

                dsp = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());
                dspEditor = dsp.edit ();

                //player info
                dspEditor.putString ("name", setup_et_name.getText().toString());
                dspEditor.putInt("level", player.getData().getLevel());
                dspEditor.putInt("XP", player.getData().getXP());
                dspEditor.putInt("gold", player.getData().getGold());
                dspEditor.putInt("HP", player.getData().getHP());
                dspEditor.putInt("MP", player.getData().getMP());
                dspEditor.putInt("maxHP", player.getData().getMaxHP());
                dspEditor.putInt("maxMP", player.getData().getMaxpMP());

                //player state
                dspEditor.putInt("state", 0);

                //steps
                dspEditor.putInt("steps", player.getDestination().getSteps());
                dspEditor.putInt("stepsNeeded", player.getDestination().getStepsNeeded());

                //current town, destination id
                dspEditor.putInt("currentTown", 0);
                dspEditor.putInt("destination", 0);

                //equipped wep, armor, potion id
                dspEditor.putInt("weapon", 0);
                dspEditor.putInt("armor", 0);
                dspEditor.putInt("potion", 0);

                //inventory wep, armor, potion id

                dspEditor.apply ();

                Intent townActivity = new Intent(getBaseContext(), Town.class);
                startActivity(townActivity);
                finish();
            }
        });
    }
}
