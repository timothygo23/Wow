package com.agorda.wow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agorda.wow.util.NotificationUtil;

public class Fight extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        button = (Button)findViewById(R.id.fight_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change player state back to walking
                Intent adventureIntent = new Intent(getBaseContext(), Adventure.class);
                startActivity(adventureIntent);
                finish();
            }
        });
    }

}
