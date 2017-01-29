package com.example.steve.plottest;

import android.app.Service;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.LoginFilter;
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
        Log.i(TAG, "onCreate: create'");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: beging the function.");

//        singleprocess = new SingleSocketProcess();
        Log.i(TAG, "onStartCommand: Create new SingleSocketProcess thread.");
//        singleprocess.run();
        new SingleSocketProcess().start();

        Log.i(TAG, "onStartCommand: After run().");

//        StartServer();// Should not be a loop for ever...
        //TODO: Add a new thread to accept the socket.
        return super.onStartCommand(intent, flags, startId);
    }


    private Boolean StartServer()
    {
        try{
           server = new ServerSocket(PORT);
            Log.i(TAG, "StartServer: " + server.toString());
            while(true)
            {
                tmp_socket = server.accept();
            }
       }catch (Exception e)
        {
            Log.i(TAG, "StartServer: "+e.getMessage());
        }
        return true;
    }

    class SingleSocketProcess extends Thread {
        @Override
        public void run() {
            
            while(true)
            {
                try{
                    
                    if(mList.size()>0)
                    {
                        Log.i(TAG, "run: " + mList.size());
                    }
//                    Log.i(TAG, "run: mList.size() == 0");
                }catch (Exception e)
                {

                    Log.d(TAG, "StartServer: "+e.getMessage());
                }
            }
        }
    }
}
