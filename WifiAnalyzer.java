package com.example.android.sometests;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;
import java.lang.String;

import java.util.List;

public class WifyAnalyzer {

    private SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);


    public void initializeWiFiListener() {

        String connectivity_context = Context.WIFI_SERVICE;

        final WifiManager wifi = (WifiManager) getSystemService(connectivity_context);

        if (!wifi.isWifiEnabled()) {

            if (wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {

                wifi.setWifiEnabled(true);
            }
        }

        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {

                WifiInfo info = wifi.getConnectionInfo();
                String ssid = info.getSSID();
                int rssi = info.getRssi();
                int speed = info.getLinkSpeed();
                TextView t1 = (TextView) findViewById(R.id.textView);
                t1.setText(ssid + " " + Integer.toString(rssi) + " " + Integer.toString(speed));
            }

        }, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
    }

}
