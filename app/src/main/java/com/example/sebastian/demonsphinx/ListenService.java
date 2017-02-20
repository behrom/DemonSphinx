package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ListenService extends Service {
    // vibrator (test czy service dziala)
    private Vibrator vibrator;

    private SphinxRecognise recognise;

    // zmienna odpowiedzialna za wysylanie komunikatow broadcast w aplikacji
    private LocalBroadcastManager broadcaster;

    // ??
    static final public String COPA_RESULT = "com.controlj.copame.backend.COPAService.REQUEST_PROCESSED";

    // ??
    static final public String COPA_MESSAGE = "com.controlj.copame.backend.COPAService.COPA_MSG";

    // zmienna odpowiedzialna za konfigurację vibrator
    private long[] vibPattern = {0, 200, 0};

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

        recognise = new SphinxRecognise(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("HelloServices", "Called onStartCommand() method.");
        vibrator.vibrate(vibPattern, 0);
        sendResult("Service started");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("HelloServices", "Called onDestroy() method.");
        vibrator.cancel();

        recognise.stopRecognition();

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
