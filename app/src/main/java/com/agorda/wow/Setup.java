package com.agorda.wow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setup extends AppCompatActivity {
    private Button setup_button_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        setup_button_create = (Button)findViewById(R.id.setup_button_create);
        setup_button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent for town activity
                //create new player -> shared pref

                Intent townActivity = new Intent(getBaseContext(), Town.class);
                startActivity(townActivity);
                finish();
            }
        });
    }
}
