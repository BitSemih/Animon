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
    private int turn, background, battlePlatform, messageBox, count = 0, messageDelay;
    private Bitmap backgroundBitmap, platformBitmap, messageBoxBitmap;
    private Game game;
    private String welcomeMessage;
    private float frame;
    private Typeface pokemonfont;
    private Paint p;
    private char[] welcomeMessageLetters;

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
        welcomeMessage = "";
        String s = "A wild Mees appeared!";
        this.welcomeMessageLetters = s.toCharArray();
    }

    public void startBattle(Animon playerAnimon, Animon npcAnimon) {

    }

    public void endBattle() {

    }

    @Override
    public void tick() {
        frame += 0.05;
        if ((int) frame % 2 == 1) {
            //do walk stuff
            addLetterToWelcomeMessage();
            frame = 0;
            if (messageDelay < 10){
                messageDelay++;
            }
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
            gv.getCanvas().drawText(welcomeMessage, 2+(welcomeMessage.length()*0.25f)/scaleFactor, (game.getHeight()-2.5f)/scaleFactor, p);
            gv.getCanvas().restore();
        }
    }

    public void addLetterToWelcomeMessage(){
        if (messageDelay == 10){
            if (count < welcomeMessageLetters.length){
                welcomeMessage += welcomeMessageLetters[count];
            }
            count++;
        }
    }
}
