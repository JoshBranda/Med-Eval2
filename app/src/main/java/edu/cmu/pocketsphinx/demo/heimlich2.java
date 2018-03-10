package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class heimlich2 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private MediaPlayer heimlich2;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich2);

        heimlich2 = MediaPlayer.create(heimlich2.this,R.raw.heimlich2);
        heimlich2.setOnCompletionListener(this);
        heimlich2.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich3.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (heimlich2.isPlaying()) {
            heimlich2.stop();
        }
        super.onDestroy();
    }

    public void back(View view) {
        if (heimlich2.isPlaying()) {
            heimlich2.stop();
        }

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void pause(View view) {
        if (heimlich2.isPlaying()) {
            heimlich2.pause();
            position = heimlich2.getCurrentPosition();
        }
        else {
            heimlich2.seekTo(position);
            heimlich2.start();
        }
    }

    public void next(View view) {
        if (heimlich2.isPlaying()) {
            heimlich2.stop();
        }

        Intent intent = new Intent(this, heimlich3.class);
        startActivity(intent);
    }
}
