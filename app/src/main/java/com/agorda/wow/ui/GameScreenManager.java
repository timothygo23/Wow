package com.agorda.wow.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.agorda.wow.ui.screen.Screen;
import com.agorda.wow.ui.ui_element.UiView;

import java.util.Stack;

/**
 * Created by Timothy on 07/12/2017.
 */

public class GameScreenManager {
    private Context context;
    private Stack<Screen> screenStack;
    private MainThreadListener mainThreadListener;

    private View currentView;

    public GameScreenManager(Context context){
        screenStack = new Stack<Screen>();
        this.context = context;
    }

    public Screen getTop(){
        return screenStack.peek();
    }

    public void push(Screen screen){
        screenStack.push(screen);
    }

    public void pop(Screen screen, View view){
        screenStack.pop();
        mainThreadListener.removeViews();
        screenStack.push(screen);
        if(view != null) {
            mainThreadListener.addView(view);
            this.currentView = view;
            screen.initViewElements();
        }
    }

    public void setMainThreadListener(MainThreadListener mainThreadListener){
        this.mainThreadListener = mainThreadListener;
    }

    public View getCurrentView(){return currentView;}

}
