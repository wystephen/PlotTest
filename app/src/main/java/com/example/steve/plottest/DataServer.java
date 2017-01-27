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
    private Socket tmp_socket = null;
    private ExecutorService mExecutorService = null; // thread pool;

    
    private SingleSocketProcess singleprocess;

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
        Log.d(TAG, "onCreate: create'");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: beging the function.");
//        singleprocess.run();
//        StartServer();
        return super.onStartCommand(intent, flags, startId);
    }

    private Boolean StartServer()
    {
        try{
           server = new ServerSocket(PORT);
            Log.d(TAG, "StartServer: " + server.toString());
            while(true)
            {
                tmp_socket = server.accept();
            }
       }catch (Exception e)
        {
            Log.d(TAG, "StartServer: "+e.getMessage());
        }
        return true;
    }
    class SingleSocketProcess implements Runnable{
        @Override
        public void run() {
            
            while(true)
            {
                try{
                    
                    if(mList.size()>0)
                    {
                        Log.d(TAG, "run: " + mList.size());
                    }
                }catch (Exception e)
                {
                    Log.d(TAG, "StartServer: "+e.getMessage());
                }
            }
        }
    }
}
