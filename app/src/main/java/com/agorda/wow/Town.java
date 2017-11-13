package com.agorda.wow;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agorda.wow.gameElements.player.Destination;
import com.agorda.wow.gameElements.player.Player;
import com.agorda.wow.util.NotificationUtil;
import com.agorda.wow.util.StepCounter;

public class Town extends AppCompatActivity{
    private Button town_button_adventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        town_button_adventure = (Button)findViewById(R.id.town_button_adventure);
        town_button_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set player state to camping
                Intent adventureActivity = new Intent(getBaseContext(), Adventure.class);
                startActivity(adventureActivity);
                finish();
            }
        });
    }

}
