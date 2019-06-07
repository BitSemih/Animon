package nl.saxion.playground.animon.game;

import android.util.Log;

import nl.saxion.playground.animon._lib.GameModel;

public class Game extends GameModel {

    private String jsonString =  "";

    public Game(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public void start() {
        addEntity(new KeyEntity(this));
        //addEntity(new Tiles(this));
        Collision collision = new Collision();
        addEntity(new Map(jsonString, this, collision));

        addEntity(new Player(this, collision));
        addEntity(new Menu(this));

//        addEntity(new Bear(this, "Bear", 100, 2, 0));

        Log.i("Game virtual size:", getWidth() + " / " + getHeight());
        Log.i("Game actual size:", actualWidth + " / " + actualHeight);
    }

    @Override
    public float getWidth() {
        // Width is always 16 units.
        return 16f;
    }

    @Override
    public float getHeight() {
        // Height fills actual screen size, but is based on width scaling.
        return actualHeight/actualWidth * getWidth();
    }
}
