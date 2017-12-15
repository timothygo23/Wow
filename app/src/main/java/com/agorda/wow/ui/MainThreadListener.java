package com.agorda.wow.ui;

import android.view.View;

/**
 * Created by Timothy on 16/12/2017.
 */

public interface MainThreadListener {
    void addView(View view);
    void removeViews();
}
