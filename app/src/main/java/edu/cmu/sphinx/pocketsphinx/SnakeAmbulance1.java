package edu.cmu.sphinx.pocketsphinx;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.cmu.pocketsphinx.demo.PocketSphinxActivity;
import edu.cmu.pocketsphinx.demo.R;

public class SnakeAmbulance1 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{
    MediaPlayer SnakeAmbulance1;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_ambulance1);

        position = 1;
        SnakeAmbulance1 = MediaPlayer.create(SnakeAmbulance1.this,R.raw.snakeambulance);
        SnakeAmbulance1.setOnCompletionListener(this);
        SnakeAmbulance1.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (SnakeAmbulance1.isPlaying()) {
            SnakeAmbulance1.stop();
        }
        super.onDestroy();
    }

    public void back(View view) {
        if (SnakeAmbulance1.isPlaying()) {
            SnakeAmbulance1.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

    public void pause(View view) {
        if (SnakeAmbulance1.isPlaying()) {
            SnakeAmbulance1.pause();
            position = SnakeAmbulance1.getCurrentPosition();
        }
        else {
            SnakeAmbulance1.seekTo(position);
            SnakeAmbulance1.start();
        }
    }

    public void mainMenu(View view) {
        if (SnakeAmbulance1.isPlaying()) {
            SnakeAmbulance1.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }
}
