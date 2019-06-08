package nl.saxion.playground.animon.game.menu;

import android.graphics.Typeface;

import nl.saxion.playground.animon.game.Game;
import nl.saxion.playground.animon.game.menu.MenuItem;

public class SaveGame extends MenuItem {

    //Logic for saving the game
    public SaveGame(String name, Game game, Typeface pokemonfont) {
        super(name, game, pokemonfont);
    }
}
