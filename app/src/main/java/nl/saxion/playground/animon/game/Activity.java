package nl.saxion.playground.animon.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon._lib.RepeatListener;

public class Activity extends AppCompatActivity {

    Animon game;
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
            game = (Animon) savedInstanceState.getSerializable("game");
        } else {
            game = new Animon();
        }

        findViewById(R.id.buttonRight).setOnTouchListener(new RepeatListener(1000, 1000, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("right");
            }
        }));

        findViewById(R.id.buttonLeft).setOnTouchListener(new RepeatListener(100, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("left");
            }
        }));

        findViewById(R.id.buttonUp).setOnTouchListener(new RepeatListener(100, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("up");
            }
        }));
//
        findViewById(R.id.buttonDown).setOnTouchListener(new RepeatListener(100, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("down");
            }
        }));
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
