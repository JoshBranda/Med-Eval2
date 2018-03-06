package edu.cmu.pocketsphinx.demo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class heimlich4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich4);

        MediaPlayer heimlich4 = MediaPlayer.create(heimlich4.this,R.raw.heimlich4);
        heimlich4.start();
    }

}
