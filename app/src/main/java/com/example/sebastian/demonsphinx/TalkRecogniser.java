package com.example.sebastian.demonsphinx;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;

public class TalkRecogniser implements RecognitionListener {
    private static final String KWS_SEARCH = "wakeup";
    private static final String COMPUTER_SEARCH = "COMPUTER";
    private static final String PHONE_SEARCH = "PHONE";

    private Context context;
    // zmienna przechowujaca wysluchana wartosc
    private String result = "NO RESULT";

    // zmienna odpowiedzialna za rozpoznawanie mowy dla PocketSphinxa
    private SpeechRecognizer recognizer;

    // konstruktor
    public TalkRecogniser(Context con) {
        context = con;

        runRecognizerSetup();
    }

    public void stopRecognition() {
        recognizer.stop();
        recognizer.shutdown();
    }

    // Metody z RecognitionListener (od PocketSphinx) potrzebne do implementacji
    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {
        if (!recognizer.getSearchName().equals(KWS_SEARCH))
            recognizer.startListening(KWS_SEARCH);
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis == null)
            return;

        result = hypothesis.getHypstr();
        Log.d("onPartialResult", "###############################" + result);

        recognizer.stop();
    }

    @Override
    public void onResult(Hypothesis hypothesis) {

        if (hypothesis != null) {
            result = hypothesis.getHypstr();
            Log.d("onResult", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + result);

            switch (result) {
                case COMPUTER_SEARCH: {
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                    computerRecognised();
                } break;

                case PHONE_SEARCH: {
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                    phoneRecognised();

                } break;

                default: {
                    Toast.makeText(context, "CANNOT RECOGNIZE WORD", Toast.LENGTH_SHORT).show();
                    recognizer.startListening(KWS_SEARCH);
                }
            }
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {
        recognizer.startListening(KWS_SEARCH);
    }

    // ustawienie sciezek do plikow dla sphinxa
    private void runRecognizerSetup() {
        // Recognizer initialization is a time-consuming and it involves IO,
        // so we execute it in async task
        new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    Assets assets;
                    File assetsDir = null;
                    assets = new Assets(context);
                    assetsDir = assets.syncAssets();
                    Log.d("@@@@@@@@@@@@Assets", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+assetsDir.getAbsolutePath());

                    setupRecognizer(assetsDir);
                } catch (IOException e) {
                    return e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception result) {
                if (result != null) {
                    Toast.makeText(context, "Failed to init recognizer" + result, Toast.LENGTH_SHORT).show();
                } else {
                    recognizer.startListening(KWS_SEARCH);
                }
            }
        }.execute();
    }

    // konfiguracja sposobu sluchania oraz oczekiwania na konkretne slowo
    private void setupRecognizer(File assetsDir) {
        File modelDir = new File(assetsDir, "models");

        try {
            recognizer = SpeechRecognizerSetup.defaultSetup()
                    .setAcousticModel(new File(modelDir, "/hmm/en-us-semi"))
                    .setDictionary(new File(modelDir, "/dict/devices.dic"))
                    .setRawLogDir(assetsDir)
                    .getRecognizer();

            recognizer.addListener(this);

            // Create keyword-activation search.
            recognizer.addKeyphraseSearch(KWS_SEARCH, COMPUTER_SEARCH);

            // Create language model search.
            File languageModel = new File(modelDir, "lm/devices.lm");
            recognizer.addNgramSearch(KWS_SEARCH, languageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void computerRecognised() {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

        recognizer.startListening(KWS_SEARCH);
    }

    private void phoneRecognised() {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);

        recognizer.startListening(KWS_SEARCH);
    }
}
