package nl.saxion.playground.animon.game;

import android.graphics.Typeface;

public class SaveGame extends MenuItem {
    private Typeface pokemonfont;

    public SaveGame(String name, Game game, Typeface pokemonfont) {
        super(name, game, pokemonfont);
        this.pokemonfont = pokemonfont;
    }
}
