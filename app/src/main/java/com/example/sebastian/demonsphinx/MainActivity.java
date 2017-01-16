package com.example.sebastian.demonsphinx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton btnStartStopService;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartStopService = (ToggleButton) findViewById(R.id.btnStartStopService);

        btnStartStopService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceIntent = new Intent(buttonView.getContext(), ListenService.class);

                if(isChecked) {
                    startService(serviceIntent);
                } else {
                    stopService(serviceIntent);
                }
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s = intent.getStringExtra(ListenService.COPA_MESSAGE);
                Log.d("intent", s);
                btnStartStopService.setText(s);
            }
        };

    }


    @Override
    protected void onStart() {

        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(ListenService.COPA_RESULT)
        );
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
    }
}
