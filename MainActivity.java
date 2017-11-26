package com.example.android.sometests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.Formatter;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.net.wifi.WifiManager;
import com.example.android.sometests.Wifi;

import org.w3c.dom.Text;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.updateValues();

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    protected  void onResume(){
        super.onResume();
        this.updateValues();

    }

    protected void onDestroy(){
        super.onDestroy();
    }

    private void updateValues(){
        final WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        if (!wifi.isWifiEnabled()) {

            if (wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {

                wifi.setWifiEnabled(true);
            }
        }

        TextView ssid_value = (TextView) findViewById(R.id.SSID_value);
        ssid_value.setText(wifi.getConnectionInfo().getSSID());

        TextView ip_value = (TextView) findViewById(R.id.ip_value);
        ip_value.setText(Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress()));

        TextView speed_value = (TextView) findViewById(R.id.speed_value);
        String s = String.valueOf(wifi.getConnectionInfo().getLinkSpeed()) + " Mbit/s";
        speed_value.setText(s);


        TextView mac_value = (TextView) findViewById(R.id.MAC_value);
        mac_value.setText(Wifi.getMacAddr());

        TextView rssi = (TextView) findViewById(R.id.rssi_value);
        rssi.setText(String.valueOf(wifi.getConnectionInfo().getRssi()));

        TextView GW = (TextView) findViewById(R.id.gateway_value);
        GW.setText(Formatter.formatIpAddress(wifi.getDhcpInfo().gateway));


    }

}
