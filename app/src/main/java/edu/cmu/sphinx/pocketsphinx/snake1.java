package edu.cmu.sphinx.pocketsphinx;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cmu.pocketsphinx.demo.R;

public class snake1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake1);

        MediaPlayer snake1 = MediaPlayer.create(snake1.this,R.raw.snake1);
        snake1.start();
    }
}
