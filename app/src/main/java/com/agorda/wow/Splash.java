package com.agorda.wow;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.agorda.wow.ui.MainThreadListener;
import com.agorda.wow.ui.MainView;
import com.agorda.wow.ui.ui_element.UiView;

public class Splash extends AppCompatActivity implements MainThreadListener{
    private final int loadTime = 1500;

    private MainView mainView; //surfaceview
    private UiView uiView; //relativeview

    private View view;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        float scale_width = 1080 / getWindowManager().getDefaultDisplay().getWidth();
        float scale_height = 1920 / getWindowManager().getDefaultDisplay().getHeight();

        uiView = new UiView(getBaseContext());
        mainView = new MainView(getBaseContext(), scale_width, scale_height, this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 0: uiView.removeAllViews();
                        break;
                    case 1: uiView.addView(view);
                        break;
                }
                super.handleMessage(msg);
            }
        };

        FrameLayout frameLayout = new FrameLayout(getBaseContext());
        frameLayout.addView(mainView);
        frameLayout.addView(uiView);

        setContentView(frameLayout);

        //sets up the db (creates it when it doesn't exists yet)
        //DatabaseHelper dbh = new DatabaseHelper(this);
        //dbh.getWritableDatabase();

        /*doTransition();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //create intent for first screen
                //check shared preferences if first time user
                //if -> true
                //create setup activity
                //if -> false
                //create previous activity of user

                dsp = PreferenceManager.getDefaultSharedPreferences (getBaseContext ());

                if (!dsp.contains("state")) {
                    Intent i = new Intent (getBaseContext(), Setup.class);
                    startActivity (i);
                    finish();
                } else { //temporary previous activity
                    Intent i = null;
                    PlayerState ps = PlayerState.valueOf(dsp.getString("state", "Town"));

                    if(ps == PlayerState.TOWN || ps == PlayerState.DEAD){
                        i = new Intent(getBaseContext(), Town.class);
                    }else if(ps == PlayerState.WALKING || ps == PlayerState.CAMPING){
                        i = new Intent(getBaseContext(), Adventure.class);
                    }else if(ps == PlayerState.FIGHTING){
                        i = new Intent(getBaseContext(), Fight.class);
                    }

                    startActivity(i);
                    finish();
                }

            }
        }, loadTime);*/
    }

    public void doTransition(){
        Fade fadeIn = new Fade(Fade.IN);
        ViewGroup view = (ViewGroup)findViewById(R.id.logo);

        ImageView imageView = new ImageView(getBaseContext());
        imageView.setImageDrawable(getDrawable(R.drawable.logo));
        imageView.setScaleX(0.65f);
        imageView.setScaleY(0.65f);

        TransitionManager.beginDelayedTransition(view, fadeIn);

        view.addView(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainView.pause();
    }

    @Override
    public void addView(View view) {
        this.view = view;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void removeViews() {
        handler.sendEmptyMessage(0);
    }
}
