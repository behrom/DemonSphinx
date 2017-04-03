package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class ListenService extends Service {
    private SphinxRecogniser sphinxRecognise;

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

        Log.d("HelloServices", "Called onCreate() method.");

        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("GoogleRecogniser"));

        sphinxRecognise = new SphinxRecogniser(this);
    }

    // Our handler for received Intents. This will be called whenever an Intent
    // with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("result");
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            startRecognition();
        }
    };

    private void startRecognition(){
        sphinxRecognise = new SphinxRecogniser(this);
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

        sphinxRecognise.stopRecognition();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

        sendResult("Service stopped");
        super.onDestroy();
    }


    public void sendResult(String message) {
        Intent intent = new Intent(COPA_RESULT);
        if (message != null)
            intent.putExtra(COPA_MESSAGE, message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
