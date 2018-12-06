package com.rosyid.sensorandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//SENSOR
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor ambientTemp, humidity, pressure, lightIntentsity, proximity;
    TextView textTemperature, textHumidity, textPressure, textLight, textProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTemperature = (TextView) findViewById(R.id.AMBIENT_TEMPERATURE_reading);
        textHumidity = (TextView) findViewById(R.id.HUMIDITY_reading);
        textPressure = (TextView) findViewById(R.id.PRESSURE_reading);
        textLight = (TextView) findViewById(R.id.LIGHT_INT_reading);
        textProximity = (TextView) findViewById(R.id.PROXIMITY_reading);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        ambientTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        lightIntentsity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (ambientTemp != null) {
            textTemperature.setText("Sensor Ambient ada");
            sensorManager.registerListener(
                    AmbientTemperatureSensorListener,
                    ambientTemp,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textTemperature.setText("Sensor Ambient tidak ada");
        }

        if (humidity != null) {
            textHumidity.setText("Sensor Humidity ada");
            sensorManager.registerListener(
                    HumiditySensorListener,
                    humidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textHumidity.setText("Sensor Humidity tidak ada");
        }

        if (pressure != null) {
            textPressure.setText("Sensor tekanan ada");
            sensorManager.registerListener(
                    PressureSensorListener,
                    pressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textPressure.setText("Sensor tekanan tidak ada");
        }

        if (lightIntentsity != null) {
            textLight.setText("Sensor cahaya ada");
            sensorManager.registerListener(
                    LightIntensitySensorListener,
                    lightIntentsity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textLight.setText("Sensor cahaya tidak ada");
        }

        if (proximity != null) {
            textProximity.setText("Sensor jarak ada");
            sensorManager.registerListener(
                    ProximitySensorListener,
                    proximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textProximity.setText("Sensor jarak tidak ada");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterAllSensor();
    }

    private void unregisterAllSensor() {
        sensorManager.unregisterListener(AmbientTemperatureSensorListener);
        sensorManager.unregisterListener(HumiditySensorListener);
        sensorManager.unregisterListener(PressureSensorListener);
        sensorManager.unregisterListener(LightIntensitySensorListener);
        sensorManager.unregisterListener(ProximitySensorListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterAllSensor();
    }

    private final SensorEventListener AmbientTemperatureSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                textTemperature.setText("Ambient Temp: " + event.values[0] + " C");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private final SensorEventListener HumiditySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
                textHumidity.setText("Kelembaban: " + event.values[0] + " %");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private final SensorEventListener PressureSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
                textPressure.setText("Tekanan: " + event.values[0] + " hPa");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private final SensorEventListener LightIntensitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                textLight.setText("Intensitas cahaya: " + event.values[0] + " lux");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private final SensorEventListener ProximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                textProximity.setText("Proximity Sensor: " + event.values[0]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
