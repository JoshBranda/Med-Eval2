package edu.cmu.sphinx.pocketsphinx;

/**
 * Created by joshuasander on 3/9/18.
 */
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;
import edu.cmu.pocketsphinx.demo.DisplayMessageActivity;
import edu.cmu.pocketsphinx.demo.R;

import static android.widget.Toast.makeText;

public class SnakeVoice extends Activity implements
        RecognitionListener, MediaPlayer.OnCompletionListener {

    /* Named searches allow to quickly reconfigure the decoder */
    private static final String YES = "yes";
    public static final String MENU_SEARCH = "menu";
    private static final String NO       = "no";

    //Let PocketSphinx now which menu the user is currently in
    private String state = MENU_SEARCH;

    /* Keyword we are looking for to activate menu */
    private static final String KEYPHRASE = "oh mighty computer";

    /* Used to handle permission request */
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    public SpeechRecognizer recognizer;
    private HashMap<String, Integer> captions;

    private boolean isStarted;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        setContentView(R.layout.activity_snake_voice);
        isStarted = false;

        MediaPlayer ring= MediaPlayer.create(edu.cmu.sphinx.pocketsphinx.SnakeVoice.this,R.raw.ambulance);
        ring.setOnCompletionListener(this);
        ring.start();

        // Prepare the data for UI
        captions = new HashMap<>();
        captions.put(MENU_SEARCH, R.string.menu_caption);

        // Check if user has given permission to record audio
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
            return;
        }
        // Recognizer initialization is a time-consuming and it involves IO,
        // so we execute it in async task
        new edu.cmu.sphinx.pocketsphinx.SnakeVoice.SetupTask(this).execute();
//        recognizer.stop();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        MediaPlayer mp2 = MediaPlayer.create(this, R.raw.unsure);
        mp2.start();
        isStarted = true;

        switchSearch(MENU_SEARCH);
    }

//    public void activateRecorder(View view) {
//
//        if (isStarted) {
//            MediaPlayer mp = MediaPlayer.create(this, R.raw.finished);
//            mp.start();
//            isStarted = false;
//        }
//        else {
//            MediaPlayer mp = MediaPlayer.create(this, R.raw.unsure);
//            mp.start();
//            isStarted = true;
//        }
//
//        switchSearch(MENU_SEARCH);
//    }

    public void voiceActivate(String message) {
        if (message.equals(YES)) {
            recognizer.stop();
            Intent intent = new Intent(this, SnakeAmbulance1.class);
            startActivity(intent);
        }
        else if (message.equals(NO)) {
            recognizer.stop();
            Intent intent = new Intent(this, snake1.class);
            startActivity(intent);
        }
    }

    private static class SetupTask extends AsyncTask<Void, Void, Exception> {
        WeakReference<edu.cmu.sphinx.pocketsphinx.SnakeVoice> activityReference;
        SetupTask(edu.cmu.sphinx.pocketsphinx.SnakeVoice activity) {
            this.activityReference = new WeakReference<>(activity);
        }
        @Override
        protected Exception doInBackground(Void... params) {
            try {
                Assets assets = new Assets(activityReference.get());
                File assetDir = assets.syncAssets();
                activityReference.get().setupRecognizer(assetDir);
            } catch (IOException e) {
                return e;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Exception result) {
            if (result != null) {
            } else {
                activityReference.get().switchSearch(MENU_SEARCH);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Recognizer initialization is a time-consuming and it involves IO,
                // so we execute it in async task
                new edu.cmu.sphinx.pocketsphinx.SnakeVoice.SetupTask(this).execute();
            } else {
                finish();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (recognizer != null) {
            recognizer.cancel();
            recognizer.shutdown();
        }
    }

    /**
     * In partial result we get quick updates about current hypothesis. In
     * keyword spotting mode we can react here, in other modes we need to wait
     * for final result in onResult.
     */
    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis == null)
            return;

        String text = hypothesis.getHypstr();

        if (text.equals(KEYPHRASE))
            switchSearch(MENU_SEARCH);
        else if (text.equals(YES) && state.equals(MENU_SEARCH)) {
            switchSearch(YES);
            state = YES;
        }
        else if (text.equals(NO)) {
            switchSearch(NO);
            state = NO;
        }
        else
            return;
    }

    /**
     * This callback is called when we stop the recognizer.
     */
    @Override
    public void onResult(Hypothesis hypothesis) {
        if (hypothesis != null) {
//            String text = hypothesis.getHypstr();
//            makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    /**
     * We stop recognizer here to get a final result
     */
    @Override
    public void onEndOfSpeech() {
        if (!recognizer.getSearchName().equals(MENU_SEARCH))
            switchSearch(MENU_SEARCH);
    }

    private void switchSearch(String searchName) {
        recognizer.stop();

        if (isStarted) {

            recognizer.startListening(MENU_SEARCH);

            if (searchName.equals(YES)) {
                voiceActivate(YES);
            } else if (searchName.equals(NO)) {
                voiceActivate(NO);
            }
        }

    }

    private void setupRecognizer(File assetsDir) throws IOException {
        // The recognizer can be configured to perform multiple searches
        // of different kind and switch between them

        recognizer = SpeechRecognizerSetup.defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))

                .setRawLogDir(assetsDir) // To disable logging of raw audio comment out this call (takes a lot of space on the device)

                .getRecognizer();
        recognizer.addListener(this);

        /* In your application you might not need to add all those searches.
          They are added here for demonstration. You can leave just one.
         */

        // Create keyword-activation search.
//        recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);

        // Create grammar-based search for selection between demos
        File menuGrammar = new File(assetsDir, "digits.gram");
        recognizer.addGrammarSearch(MENU_SEARCH, menuGrammar);

    }

    @Override
    public void onError(Exception error) {
    }

    @Override
    public void onTimeout() {
        switchSearch(MENU_SEARCH);
    }
}

