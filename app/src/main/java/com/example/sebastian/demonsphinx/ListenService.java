package com.example.sebastian.demonsphinx;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ListenService extends Service {
    private Vibrator vibrator;
    private Handler handler = new Handler();

    private LocalBroadcastManager broadcaster;

    static final public String COPA_RESULT = "com.controlj.copame.backend.COPAService.REQUEST_PROCESSED";
    static final public String COPA_MESSAGE = "com.controlj.copame.backend.COPAService.COPA_MSG";

    private long[] vibPattern = {0, 200, 0};

    public ListenService() {

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcaster = LocalBroadcastManager.getInstance(this);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        Log.d("HelloServices", "Called onCreate() method.");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("HelloServices", "Called onStartCommand() method.");
        vibrator.vibrate(vibPattern, 0);
        sendResult("Service started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //Log.d("HelloServices", "Called onDestroy() method.");
        vibrator.cancel();

        sendResult("Service stopped");
        super.onDestroy();
    }


    public void sendResult(String message) {
        Intent intent = new Intent(COPA_RESULT);
        if (message != null)
            intent.putExtra(COPA_MESSAGE, message);
        broadcaster.sendBroadcast(intent);
    }
}
