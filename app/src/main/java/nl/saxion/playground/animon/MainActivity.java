package nl.saxion.playground.animon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mainMenuTheme;
    private int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuTheme = MediaPlayer.create(getApplicationContext(),R.raw.title_screen);
        mainMenuTheme.start();
        mainMenuTheme.setLooping(true);

        findViewById(R.id.buttonPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, nl.saxion.playground.animon.game.Activity.class));
                mainMenuTheme.stop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainMenuTheme.seekTo(length);
        mainMenuTheme.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainMenuTheme.pause();
        length = mainMenuTheme.getCurrentPosition();
    }
}
