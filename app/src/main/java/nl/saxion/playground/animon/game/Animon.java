package nl.saxion.playground.animon.game;

import android.util.Log;

import nl.saxion.playground.animon._lib.GameModel;

public class Animon extends GameModel {

    @Override
    public void start() {
        addEntity(new KeyEntity(this));
        addEntity(new Ground(this));
        addEntity(new Path(this));
        addEntity(new Trees(this));
        addEntity(new Player(this));

        Log.i("Animon virtual size:", getWidth() + " / " + getHeight());
        Log.i("Animon actual size:", actualWidth + " / " + actualHeight);
    }

//    //this allows us to specify our virtual size!
    @Override
    public float getWidth() {
        // Width is always 8 units.
        return 16f;
        //return 320;
    }

    @Override
    public float getHeight() {
        // Height fills actual screen size, but is based on width scaling.
        return actualHeight/actualWidth * getWidth();
        //return 640;
    }
}
