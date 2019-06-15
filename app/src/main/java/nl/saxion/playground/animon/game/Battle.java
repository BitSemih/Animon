package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.animons.Animon;

public class Battle extends Entity {
    private Animon playerAnimon, npcAnimon;
    private int turn, background, battlePlatform, messageBox;
    private Bitmap backgroundBitmap, platformBitmap, messageBoxBitmap;
    private Game game;
    private String welcomeMessage;
    private float frame;
    private Typeface pokemonfont;
    private Paint p;

    public Battle(int background, Game game, int battlePlatform, int messageBox, Typeface pokemonfont) {
        this.game = game;
        this.battlePlatform = battlePlatform;
        this.background = background;
        this.messageBox = messageBox;
        this.pokemonfont = pokemonfont;

        this.p = new Paint();
        this.p.setColor(Color.BLACK);
        this.p.setTextSize(1);
        this.p.setTypeface(pokemonfont);
        this.p.setAntiAlias(true);
        this.p.setTextAlign(Paint.Align.CENTER);
        this.p.setLinearText(true);
    }

    public void startBattle(Animon playerAnimon, Animon npcAnimon) {

    }

    public void endBattle() {

    }

    @Override
    public void tick() {
        frame += 0.01;
        if ((int) frame % 2 == 0) {
            //do walk stuff
            addLetterToWelcomeMessage(frame);
        }
    }

    @Override
    public void draw(GameView gv) {
        if (state == 1) {
            if (backgroundBitmap == null || platformBitmap == null) {
                backgroundBitmap = gv.getBitmapFromResource(background);
                platformBitmap = gv.getBitmapFromResource(battlePlatform);
                messageBoxBitmap = gv.getBitmapFromResource(messageBox);
            }
            gv.drawBitmap(backgroundBitmap, 0, 0, game.getWidth(), game.getHeight());
            gv.drawBitmap(platformBitmap, game.getWidth() - 7.5f, game.getHeight() * 0.5f, 7, 2);
            gv.drawBitmap(platformBitmap, 0.5f, game.getHeight() - 5, 8, 2);
            gv.drawBitmap(messageBoxBitmap, 0, game.getHeight() - 4, game.getWidth(), 4);

            float scaleFactor = 0.5f;

            gv.getCanvas().save();
            gv.getCanvas().scale(scaleFactor,scaleFactor);
            gv.getCanvas().drawText("test-", 3/scaleFactor, (game.getHeight()-2.5f)/scaleFactor, p);
            gv.getCanvas().restore();
        }
    }

    public void addLetterToWelcomeMessage(float frame){
        System.out.println(frame);
    }
}
