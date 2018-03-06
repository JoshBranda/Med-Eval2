package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class heimlich2 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich2);

        MediaPlayer heimlich2 = MediaPlayer.create(heimlich2.this,R.raw.heimlich2);
        heimlich2.setOnCompletionListener(this);
        heimlich2.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich3.class);
        startActivity(intent);
    }
}
