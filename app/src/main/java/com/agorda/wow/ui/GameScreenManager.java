package com.agorda.wow.ui;

import com.agorda.wow.ui.screen.Screen;

import java.util.Stack;

/**
 * Created by Timothy on 07/12/2017.
 */

public class GameScreenManager {
    private Stack<Screen> screenStack;

    public GameScreenManager(){
        screenStack = new Stack<Screen>();
    }

    public Screen getTop(){
        return screenStack.peek();
    }

    public void push(Screen screen){
        screenStack.push(screen);
    }

    public void pop(Screen screen){
        screenStack.pop();
        screenStack.push(screen);
    }

}
