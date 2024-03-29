package edu.cmu.sphinx.pocketsphinx;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.cmu.pocketsphinx.demo.PocketSphinxActivity;
import edu.cmu.pocketsphinx.demo.R;
import edu.cmu.pocketsphinx.demo.heimlich3;

public class snake1 extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    MediaPlayer snake1;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake1);

        position = 1;
        snake1 = MediaPlayer.create(snake1.this,R.raw.snake1a);
        snake1.setOnCompletionListener(this);
        snake1.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (snake1.isPlaying()) {
            snake1.stop();
        }
        super.onDestroy();
    }

    public void back(View view) {
        if (snake1.isPlaying()) {
            snake1.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }

    public void pause(View view) {
        if (snake1.isPlaying()) {
            snake1.pause();
            position = snake1.getCurrentPosition();
        }
        else {
            snake1.seekTo(position);
            snake1.start();
        }
    }

    public void mainMenu(View view) {
        if (snake1.isPlaying()) {
            snake1.stop();
        }

        Intent intent = new Intent(this, PocketSphinxActivity.class);
        startActivity(intent);
    }
}
