package nl.saxion.playground.animon.game.menu;

import android.graphics.Typeface;

import nl.saxion.playground.animon.game.Game;
import nl.saxion.playground.animon.game.menu.MenuItem;

public class LoadGame extends MenuItem {

    //Logic for loading the game
    public LoadGame(String name, Game game, Typeface pokemonfont) {
        super(name, game, pokemonfont);
    }
}
