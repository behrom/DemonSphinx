package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ListenService extends Service {
//    private SphinxRecogniser sphinxRecognise;
    private GoogleRecogniser googleRecogniser;

    // zmienna odpowiedzialna za wysylanie komunikatow broadcast w aplikacji
    private LocalBroadcastManager broadcaster;

    // ??
    static final public String COPA_RESULT = "com.controlj.copame.backend.COPAService.REQUEST_PROCESSED";

    // ??
    static final public String COPA_MESSAGE = "com.controlj.copame.backend.COPAService.COPA_MSG";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcaster = LocalBroadcastManager.getInstance(this);

        Log.d("HelloServices", "Called onCreate() method.");

//        sphinxRecognise = new SphinxRecogniser(this);
        googleRecogniser = new GoogleRecogniser();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("HelloServices", "Called onStartCommand() method.");
        // vibrator.vibrate(vibPattern, 0);
        sendResult("Service started");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("HelloServices", "Called onDestroy() method.");

//        sphinxRecognise.stopRecognition();

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
