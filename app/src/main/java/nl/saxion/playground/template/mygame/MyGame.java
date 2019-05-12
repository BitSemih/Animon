package nl.saxion.playground.template.mygame;

import android.util.Log;

import nl.saxion.playground.template.lib.GameModel;

public class MyGame extends GameModel {

    @Override
    public void start() {
        addEntity(new SwipeEntity(this));
        //addEntity(new Background(this));
        addEntity(new Map(this));
        addEntity(new Layer1(this));
        addEntity(new Layer2(this));
        addEntity(new MyTestEntity(this));

        Log.i("MyGame virtual size:", getWidth() + " / " + getHeight());
        Log.i("MyGame actual size:", actualWidth + " / " + actualHeight);
    }

//    //this allows us to specify our virtual size!
    @Override
    public float getWidth() {
        // Width is always 8 units.
        return 320;
    }

    @Override
    public float getHeight() {
        // Height fills actual screen size, but is based on width scaling.
        //return actualHeight/actualWidth * getWidth();
        return 640;
    }
}
