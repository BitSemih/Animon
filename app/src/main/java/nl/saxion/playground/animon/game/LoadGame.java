package nl.saxion.playground.animon.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import nl.saxion.playground.animon._lib.GameView;

public class LoadGame extends MenuItem {

    //Logic for loading the game
    public LoadGame(String name, Game game, Typeface pokemonfont) {
        super(name, game, pokemonfont);
    }
}
