package com.agorda.wow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agorda.wow.gameElements.db_constants.ObjectId;
import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.gameElements.player.PlayerState;
import com.agorda.wow.gameElements.town.*;
import com.agorda.wow.util.DatabaseHelper;
import com.agorda.wow.util.GameSave;

public class Map extends AppCompatActivity {
    private Button map_button_go;

    private TextView map_tv_townname, map_tv_distance;

    private SharedPreferences dsp;
    private DatabaseHelper databaseHelper;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //back end elements
        dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        databaseHelper = new DatabaseHelper(this);

        player = GameSave.loadPlayer(dsp, databaseHelper);

        map_button_go = (Button)findViewById(R.id.map_button_go);
        map_button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create new destination (should be based on the choice of the player)
                com.agorda.wow.gameElements.town.Town destinationTown = databaseHelper.getTown(ObjectId.SECOND_TOWN);

                Destination destination = new Destination(player.getCurrentTown(), destinationTown);

                player.setDestination(destination);
                player.setState(PlayerState.CAMPING);

                GameSave.save(dsp.edit(), player);

                //go to adventure intent
                Intent intent = new Intent(getBaseContext(), Adventure.class);
                startActivity(intent);
                finish();
            }
        });

        setUp();
    }

    public void setUp(){
        map_tv_townname = (TextView)findViewById(R.id.map_tv_townname);
        map_tv_distance = (TextView)findViewById(R.id.map_tv_distance);

        //right now it's hard coded
        com.agorda.wow.gameElements.town.Town destinationTown = databaseHelper.getTown(ObjectId.SECOND_TOWN);
        map_tv_townname.setText(destinationTown.getName());
        map_tv_distance.setText((int)(destinationTown.getLocation() - player.getCurrentTown().getLocation())*Destination.coversion + " steps");
    }
}
