package com.agorda.wow;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Timothy on 16/12/2017.
 */

public class UiView extends RelativeLayout {

    public UiView(Context context) {
        super(context);
        TextView textView = new TextView(context);
        textView.setText("TANGINA MO");
        textView.setTextSize(30f);
        textView.setTextColor(Color.WHITE);
        addView(textView);
    }

    public void switchView(){
        removeAllViews();
    }

}
