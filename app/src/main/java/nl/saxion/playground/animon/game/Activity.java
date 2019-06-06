package nl.saxion.playground.animon.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon._lib.RepeatListener;

public class Activity extends AppCompatActivity {

    Game game;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animon);
        // In this example, we don't require a Layout or any other Android Views than
        // are custom GameCanvas.
        gameView = findViewById(R.id.gameView);

        // If a running game has been serialized (because it has been paused for
        // a long time, or because of an orientation change), recreate the Game
        // object from the serialized bundle.
        if (savedInstanceState!=null && savedInstanceState.containsKey("game")) {
            game = (Game) savedInstanceState.getSerializable("game");
        } else {
            game = new Game(loadJSONFromAsset());
        }

        findViewById(R.id.buttonRight).setOnTouchListener(new RepeatListener(10, 10, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("right");
            }
        }));

        findViewById(R.id.buttonLeft).setOnTouchListener(new RepeatListener(10, 10, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("left");
            }
        }));

        findViewById(R.id.buttonUp).setOnTouchListener(new RepeatListener(10, 10, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("up");
            }
        }));
//
        findViewById(R.id.buttonDown).setOnTouchListener(new RepeatListener(10, 10, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("down");
            }
        }));

        findViewById(R.id.buttonMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getEntity(KeyEntity.class).onKeyPress("menu");
            }
        });

        findViewById(R.id.buttonA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getEntity(KeyEntity.class).onKeyPress("a");
            }
        });

        findViewById(R.id.buttonB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getEntity(KeyEntity.class).onKeyPress("b");
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("map.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.setGame(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.setGame(null);
    }
}
