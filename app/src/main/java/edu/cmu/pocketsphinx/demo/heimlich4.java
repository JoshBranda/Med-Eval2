package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class heimlich4 extends AppCompatActivity {

    MediaPlayer heimlich4;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heimlich4);

        position = 1;
        heimlich4 = MediaPlayer.create(heimlich4.this,R.raw.heimlich4);
        heimlich4.start();
    }

    @Override
    protected void onDestroy() {
        if (heimlich4.isPlaying()) {
            heimlich4.stop();
        }
        super.onDestroy();
    }

    public void back(View view) {
        if (heimlich4.isPlaying()) {
            heimlich4.stop();
        }

        Intent intent = new Intent(this, heimlich3.class);
        startActivity(intent);
    }

    public void pause(View view) {
       if (heimlich4.isPlaying()) {
           heimlich4.pause();
           position = heimlich4.getCurrentPosition();
       }
       else {
           heimlich4.seekTo(position);
           heimlich4.start();
       }
    }

    public void mainMenu(View view) {
        if (heimlich4.isPlaying()) {
            heimlich4.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

}
