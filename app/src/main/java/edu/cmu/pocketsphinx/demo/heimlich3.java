package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class heimlich3 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private MediaPlayer heimlich3;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich3);

        position = 1;
        heimlich3 = MediaPlayer.create(heimlich3.this,R.raw.heimlich3);
        heimlich3.setOnCompletionListener(this);
        heimlich3.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich4.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (heimlich3.isPlaying()) {
            heimlich3.stop();
        }
        super.onDestroy();
    }

    public void back(View view) {
        if (heimlich3.isPlaying()) {
            heimlich3.stop();
        }

        Intent intent = new Intent(this, heimlich2.class);
        startActivity(intent);
    }

    public void pause(View view) {
        if (heimlich3.isPlaying()) {
            heimlich3.pause();
            position = heimlich3.getCurrentPosition();
        }
        else {
            heimlich3.seekTo(position);
            heimlich3.start();
        }
    }

    public void next(View view) {
        if (heimlich3.isPlaying()) {
            heimlich3.stop();
        }

        Intent intent = new Intent(this, heimlich4.class);
        startActivity(intent);
    }
}
