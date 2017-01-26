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

    private XYPlot plot;
    private static final String TAG = "MainActivity";


    private XYPlot dynamicPlot;
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
                    Log.i(TAG, "onOptionsItemSelected: generate _random" + (rnd.nextInt(80)));
                    break;
                }
                break;
            case R.id.showdynamic:
                Intent aintent = new Intent(MainActivity.this,DynamicXYPlotActivity.class);
                startActivity(aintent);
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

        plot = (XYPlot) findViewById(R.id.plot);

        final Number[] domainLabels = {1, 2, 3, 6, 7, 5, 4, 3, 2, 1.5};
        Number[] series1Numbers = {1, 4, 2, 7, 4, 14, 11, 32, 15, 54};
        Number[] series2Numbers = {5, 2, 10, 5, 20, 10, 40, 20, 80, 40};

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");
        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels);

        LineAndPointFormatter series2Format =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels_2);

        series2Format.getLinePaint().setPathEffect(new DashPathEffect(new float[]{

                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20),
                PixelUtils.dpToPix(15)}, 0));

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        series2Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
        plot.addSeries(series2, series2Format);

//        plot.addListener()

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });


    }
}
