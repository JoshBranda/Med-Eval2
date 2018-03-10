package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DisplayMessageActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private MediaPlayer heimlich1;
    private int position;

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

    public void back(View view) {
        if (heimlich1.isPlaying()) {
            heimlich1.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

    public void pause(View view) {
        if (heimlich1.isPlaying()) {
            heimlich1.pause();
            position = heimlich1.getCurrentPosition();
        }
        else {
            heimlich1.seekTo(position);
            heimlich1.start();
        }
    }

    public void next(View view) {
        if (heimlich1.isPlaying()) {
            heimlich1.stop();
        }

        Intent intent = new Intent(this, heimlich2.class);
        startActivity(intent);
    }
}
