package com.example.steve.plottest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DataServer extends Service {
    public DataServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
