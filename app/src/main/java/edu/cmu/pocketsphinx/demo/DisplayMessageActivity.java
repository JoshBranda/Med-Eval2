package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private MediaPlayer heimlich1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        heimlich1 = MediaPlayer.create(DisplayMessageActivity.this,R.raw.heimlich1);
        heimlich1.setOnCompletionListener(this);
        heimlich1.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich2.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (heimlich1.isPlaying()) {
            heimlich1.stop();
        }
        super.onDestroy();
    }
}
