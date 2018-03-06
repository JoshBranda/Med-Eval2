package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class heimlich3 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich3);

        MediaPlayer heimlich3 = MediaPlayer.create(heimlich3.this,R.raw.heimlich3);
        heimlich3.setOnCompletionListener(this);
        heimlich3.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich4.class);
        startActivity(intent);
    }
}
