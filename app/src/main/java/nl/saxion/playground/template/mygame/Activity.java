package nl.saxion.playground.template.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;

public class Activity extends AppCompatActivity {

    MyGame game;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // In this example, we don't require a Layout or any other Android Views than
        // are custom GameCanvas.
        gameView = new GameView(this);
        setContentView(gameView);

        // If a running game has been serialized (because it has been paused for
        // a long time, or because of an orientation change), recreate the Game
        // object from the serialized bundle.
        if (savedInstanceState!=null && savedInstanceState.containsKey("game")) {
            game = (MyGame) savedInstanceState.getSerializable("game");
        } else {
            game = new MyGame();
        }
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
