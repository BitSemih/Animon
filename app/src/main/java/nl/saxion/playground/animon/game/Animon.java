package nl.saxion.playground.animon.game;

import android.util.Log;

import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameModel;

public class Animon extends GameModel {

    private ArrayList<OnScreenButtons> moveButtons = new ArrayList<>(4);

    @Override
    public void start() {

        OnScreenButtons down = new OnScreenButtons(R.drawable.button_down, "down", 3,24);
        OnScreenButtons up = new OnScreenButtons(R.drawable.button_up, "up", 3,20);
        OnScreenButtons left = new OnScreenButtons(R.drawable.button_left, "left", 1,22);
        OnScreenButtons right = new OnScreenButtons(R.drawable.button_right, "right", 5,22);

        moveButtons.add(down);
        moveButtons.add(up);
        moveButtons.add(left);
        moveButtons.add(right);

        addEntity(down);
        addEntity(up);
        addEntity(left);
        addEntity(right);

        addEntity(new Movement(this));
        addEntity(new Player(this));
        addEntity(new Tiles(this, moveButtons));

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
