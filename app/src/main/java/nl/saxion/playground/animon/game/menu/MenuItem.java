package nl.saxion.playground.animon.game.menu;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;
import nl.saxion.playground.animon.game.menu.Menu;

public abstract class MenuItem extends Entity {
    protected String name;
    private static int lastId;
    private int positionId;
    private float posX, posY;
    private Game game;
    private Typeface pokemonfont;
    private Menu onscreenMenu;

    public MenuItem(String name, Game game, Typeface pokemonfont) {
        this.name = name;
        this.positionId = lastId;
        //Set X position for each item; its always the same
        this.posX = game.getWidth()*0.75f;
        //Calculate the Y position based on the initiated order
        this.posY = 1 + ((positionId + 1) * 2);
        this.pokemonfont = pokemonfont;
        this.game = game;
        lastId++;
        this.onscreenMenu = game.getEntity(Menu.class);
    }

    //Drawing the text
    public void draw(GameView gv) {
        if (onscreenMenu.isIsmenuactive()){
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
}
