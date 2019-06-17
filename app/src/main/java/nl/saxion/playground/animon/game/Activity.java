package nl.saxion.playground.animon.game;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon._lib.RepeatListener;
import nl.saxion.playground.animon.game.menu.Menu;
import nl.saxion.playground.animon.game.menu.MenuItem;

public class Activity extends AppCompatActivity {

    Game game;
    GameView gameView;

    private boolean ismenuactive;

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
        if (savedInstanceState != null && savedInstanceState.containsKey("game")) {
            game = (Game) savedInstanceState.getSerializable("game");
        } else {
            Typeface pokemonfont = Typeface.createFromAsset(getAssets(), "pokemonfont.ttf");
            game = new Game(loadJSONFromAsset(), pokemonfont, getApplicationContext());
        }

        menuButtonsToggle();

        findViewById(R.id.buttonMenu).setOnTouchListener(new RepeatListener(250, 250, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getEntity(KeyEntity.class).onKeyPress("menu");
                if (ismenuactive){
                    ismenuactive = false;
                } else {
                    ismenuactive = true;
                }
                menuButtonsToggle();
            }
        }));

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

    public void menuButtonsToggle(){
        if (ismenuactive){
            //When the menu is open assign up and down to menu control buttons
            findViewById(R.id.buttonUp).setOnTouchListener(new RepeatListener(500, 500, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    game.getEntity(KeyEntity.class).onKeyPress("onMenuUp");
                }
            }));

            findViewById(R.id.buttonDown).setOnTouchListener(new RepeatListener(500, 500, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    game.getEntity(KeyEntity.class).onKeyPress("onMenuDown");
                }
            }));

            //Disable left and right buttons
            findViewById(R.id.buttonRight).setEnabled(false);

            findViewById(R.id.buttonLeft).setEnabled(false);

            System.out.println("DISABLED");

        } else {
            //When menu is not open activated all buttons to movement
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

            //Enable left and right buttons
            findViewById(R.id.buttonRight).setEnabled(true);

            findViewById(R.id.buttonLeft).setEnabled(true);

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

        }
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
