package com.example.sebastian.demonsphinx;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;

import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;


/**
 * Created by Behrom on 16.01.17.
 */

public class SphinxRecognise implements RecognitionListener {

    private static final String KWS_SEARCH = "KEYPHRASE";
    private static final String KEYPHRASE = "COMPUTER";


    private Context context;
    // zmienna przechowujaca wysluchana wartosc
    private String result;

    // zmienna odpowiedzialna za rozpoznawanie mowy dla PocketSphinxa
    private SpeechRecognizer recognizer;

    public SphinxRecognise(Context con) {
        context = con;

        runRecognizerSetup();
    }

    public String getResult() {

        return result;
    }

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

            }
        }.execute();
    }

    private void setupRecognizer(File assetsDir) {
        File modelDir = new File(assetsDir, "models");
        try {
            recognizer = defaultSetup()
                    .setAcousticModel(new File(modelDir, "/hmm/en-us-semi"))
                    .setDictionary(new File(modelDir, "/dict/devices.dic"))
                    .setRawLogDir(assetsDir).setKeywordThreshold(1e-40f)
                    .getRecognizer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            recognizer.addListener(this);

            // Create keyword-activation search.
            recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);

            // Create language model search.
            File languageModel = new File(modelDir, "lm/devices.lm");
            recognizer.addNgramSearch(KWS_SEARCH, languageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        recognizer.stop();
    }

    public void stopRecognition() {
        if (recognizer != null) {
            recognizer.cancel();
            recognizer.shutdown();
        }
    }

    // Metody z RecognitionListener (od PocketSphinx) potrzebne do implementacji
    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {
        reset();
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis != null)
        {
            result = hypothesis.getHypstr();
            Log.d("SphinxRecognise", result);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResult(Hypothesis hypothesis) {

        if (hypothesis != null)
        {
            result = hypothesis.getHypstr();
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            Log.d("SphinxRecognise", result);
        }
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {

    }
}
