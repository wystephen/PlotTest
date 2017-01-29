package com.example.steve.plottest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.*;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;

import java.net.NetworkInterface.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


//    private MyPlotUpdater plotUpdater

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showip:
                Toast.makeText(this, "Show the info", Toast.LENGTH_LONG).show();
                //ToDo :Show ip here.
                try {
                    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

                    InetAddress ia = null;
                    Log.v(TAG, "onOptionsItemSelected: 1");

                    Log.d(TAG, "onOptionsItemSelected: " + nis.toString());

                    while (nis.hasMoreElements()) {
                        NetworkInterface ni = (NetworkInterface) nis.nextElement();
                        Enumeration<InetAddress> ias = ni.getInetAddresses();
                        while (ias.hasMoreElements()) {
                            ia = ias.nextElement();
                            String the_ip = ia.getHostAddress();
//                            Log.d(TAG, the_ip);
                            Toast.makeText(this, the_ip, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onOptionsItemSelected: " + the_ip);
                        }

                    }
                } catch (SocketException e) {
                    Log.i(TAG, "SOCKET ERROR");
                }

                break;
            case R.id.generate_random:
                Random rnd  = new Random();
                for(int k = 0;k<10;++k)
                {
                    Log.i(TAG, "onOptionsItemSelected: generate_random " + (rnd.nextInt(80)));
                    break;
                }
                break;

            case R.id.showdynamic:
                Log.i(TAG, "onOptionsItemSelected: Show Dynamic data test activity");
                Intent aintent = new Intent(MainActivity.this,DynamicXYPlotActivity.class);
                startActivity(aintent);
                break;

            case R.id.starttcpserver:
//                Intent tcp_intent = new Intent("com.example.steve.plottest.tcpserver");
                Intent tcp_intent = new Intent(this,DataServer.class);
                startService(tcp_intent);
                Log.i(TAG, "onOptionsItemSelected: Try to open new server");
                break;
            case R.id.stoptcpserver:
                Intent tcp_intent2 = new Intent(this,DataServer.class);
                stopService(tcp_intent2);


                break;

            default:
                break;
        }
//        return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button button_showServer = new Button(R.id.ShowServer);



    }
}
