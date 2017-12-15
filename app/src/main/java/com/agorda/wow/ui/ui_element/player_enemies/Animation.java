package com.agorda.wow.ui.ui_element.player_enemies;

import android.graphics.Bitmap;

/**
 * Created by Timothy on 15/12/2017.
 */

public class Animation {
    private double frameDuration;
    private double currentFrameDuration;
    private int frames, current_frame;
    private Bitmap[] animation;

    public Animation(double frameDuration, int frames){
        this.frameDuration = frameDuration;
        this.currentFrameDuration = frameDuration;
        this.frames = frames;
        current_frame = 0;
    }

    public void setAnimation(Bitmap[] animation){
        this.animation = animation;
    }

    public Bitmap getCurrentFrame(){
        return animation[current_frame];
    }

    public void animate(double deltaTime){
        currentFrameDuration -= 10;
        if(currentFrameDuration <= 0){
            current_frame ++;
            if(current_frame >= frames)
                current_frame = 0;
            currentFrameDuration = frameDuration;
        }
    }

    public void reset(){
        current_frame = 0;
    }
}
