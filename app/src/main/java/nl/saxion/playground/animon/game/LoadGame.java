package nl.saxion.playground.animon.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import nl.saxion.playground.animon._lib.GameView;

public class LoadGame extends MenuItem {
    private Typeface pokemonfont;

    public LoadGame(String name, Game game, Typeface pokemonfont) {
        super(name, game);
        this.pokemonfont = pokemonfont;
    }

    public void draw(GameView gv) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(0.5f);
        p.setTypeface(pokemonfont);
        p.setAntiAlias(true);
        p.setTextAlign(Paint.Align.CENTER);
        p.setLinearText(true);

        gv.getCanvas().drawText(name, getPosX(), getPosY(), p);
    }
}
