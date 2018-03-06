package edu.cmu.pocketsphinx.demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        MediaPlayer heimlich1 = MediaPlayer.create(DisplayMessageActivity.this,R.raw.heimlich1);
        heimlich1.setOnCompletionListener(this);
        heimlich1.start();

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        try{
//            mPlayer.setDataSource(openFileInput(fileName).getFD());
//            mPlayer.prepare();
//            mPlayer.setOnCompletionListener(this);
//            mPlayer.start();
//        } catch (Exception e){
//            e.printStackTrace();
//            Log.e("player", "error playing sound: "+fileName);
//            return false;
//        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(this, heimlich2.class);
        startActivity(intent);
    }
}
