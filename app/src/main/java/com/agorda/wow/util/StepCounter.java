package com.agorda.wow.util;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.agorda.wow.gameElements.player.Player;

import java.util.Arrays;

/**
 * Created by Timothy on 07/11/2017.
 */

public class StepCounter implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor sensor;
    private StepCounterListener scl;

    private boolean onResume;
    private boolean isActive;

    public StepCounter(SensorManager sensorManager, StepCounterListener scl){
        this.sensorManager = sensorManager;
        this.scl = scl;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    public void start(){
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        onResume = true;
        isActive = true;
    }

    public void stop(){
        sensorManager.unregisterListener(this, sensor);
        isActive = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_STEP_DETECTOR)
            if(!onResume)
                scl.onStep();

        onResume = false;
    }

    public boolean isActive(){
        return isActive;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
