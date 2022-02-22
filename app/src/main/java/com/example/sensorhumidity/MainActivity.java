package com.example.sensorhumidity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorHum;
    private TextView mTextSensorHum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //get textview and assign them to their respective variables
        mTextSensorHum = (TextView) findViewById(R.id.sensor_hum);
        //get instances of the default relative humidity
        mSensorHum = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        //get error string
        String sensor_error = getResources().getString(R.string.error_no_sensor);
        if(mSensorHum == null){
            mTextSensorHum.setText(sensor_error);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        //register sensor listener
        if(mSensorHum != null){
            mSensorManager.registerListener(this, mSensorHum, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener(this); //unregister listener
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];
        mTextSensorHum.setText(getResources().getString(R.string.sensor_hum, currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}