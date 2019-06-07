package nl.saxion.playground.animon.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public abstract class MenuItem extends Entity {
    protected String name;
    private static int lastId;
    private int positionId;
    private float posX, posY;
    private Game game;
    private Typeface pokemonfont;

    public MenuItem(String name, Game game, Typeface pokemonfont) {
        this.name = name;
        this.positionId = lastId;
        this.posX = game.getWidth()*0.75f;
        this.posY = 1 + ((positionId + 1) * 2);
        this.pokemonfont = pokemonfont;
        this.game = game;
        lastId++;
    }

    public void draw(GameView gv) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(0.5f);
        p.setTypeface(pokemonfont);
        p.setAntiAlias(true);
        p.setTextAlign(Paint.Align.CENTER);
        p.setLinearText(true);

        gv.getCanvas().drawText(name, posX, posY, p);
    }
}
