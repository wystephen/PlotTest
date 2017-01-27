package com.example.steve.plottest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.SocketHandler;
import java.net.Socket;
import java.net.ServerSocket;

public class DataServer extends Service {

    private static final int PORT = 21994;

    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private Socket socket = null;
    private ExecutorService mExecutorService = null; // thread pool;


    private static final String TAG = "DataServer";

//    SocketHandler
    public DataServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);

        return 0;
    }

    private Boolean StartServer()
    {
        try{


        }catch (Exception e)
        {
            Log.d(TAG, "StartServer: "+e.getMessage());
        }

        return true;
    }
}
